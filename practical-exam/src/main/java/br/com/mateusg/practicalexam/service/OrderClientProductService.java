package br.com.mateusg.practicalexam.service;

import br.com.mateusg.practicalexam.exception.InvalidGivenIdException;
import br.com.mateusg.practicalexam.exception.NotEnoughLimitException;
import br.com.mateusg.practicalexam.exception.ProductAlreadyInOrderException;
import br.com.mateusg.practicalexam.model.Client;
import br.com.mateusg.practicalexam.model.OrderClientProduct;
import br.com.mateusg.practicalexam.model.Product;
import br.com.mateusg.practicalexam.repository.OrderClientProductRepository;
import br.com.mateusg.practicalexam.view.AmountProductOrderIds;
import br.com.mateusg.practicalexam.view.ProductOrderIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class OrderClientProductService {

    @Autowired
    private OrderClientProductRepository orderClientProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    public List<OrderClientProduct> findAll() {
        return orderClientProductRepository.findAll();
    }

    public void create(OrderClientProduct orderClientProduct) {
        executeValidations(orderClientProduct);

        orderClientProductRepository.save(orderClientProduct);
    }

    private void executeValidations(OrderClientProduct orderClientProduct){
        Long idClient = orderClientProduct.getClients().getIdClient();
        Long idProduct = orderClientProduct.getProducts().getIdProduct();
        Long idOrder = orderClientProduct.getOrders().getIdOrder();

        doesClientProductAndOrderExists(idClient, idProduct, idOrder);
        executeProductExistsInOrderValidation(idProduct, idOrder);
        executeIsCreditLimitEnoughValidation(idClient, idProduct);
    }

    private void doesClientProductAndOrderExists(Long idClient, Long idProduct, Long idOrder){
        if(!clientService.existsById(idClient)){
            throw new InvalidGivenIdException(String.format("Client id %d does not exists.", idClient));
        }

        if(!productService.existsById(idProduct)){
            throw new InvalidGivenIdException(String.format("Product id %d does not exists.", idProduct));
        }

        if(!orderService.existsById(idOrder)){
            throw new InvalidGivenIdException(String.format("Order id %d does not exists.", idOrder));
        }
    }

    private void executeProductExistsInOrderValidation(Long idProduct, Long idOrder){
        if(doesProductExistsInOrder(idProduct, idOrder)){
            throw new ProductAlreadyInOrderException(
                    "This product is already in the order! Try to alter the amount of the existing product."
            );
        }
    }

    private boolean doesProductExistsInOrder(Long idProduct, Long idOrder){
        return orderClientProductRepository.findByProductAndOrder(idProduct, idOrder).isPresent();
    }

    private void executeIsCreditLimitEnoughValidation(Long idClient, Long idProduct){
        Client client = clientService.findById(idClient).get();
        Product product = productService.findById(idProduct).get();

        List<OrderClientProduct> listOrdersItems = findOrdersThisMonthByClient(
                client.getIdClient(),
                client.getInvoiceDueDate()
        );
        Double sumOrders = sumOrdersValues(listOrdersItems);
        Boolean hasEnoughCreditLimit = sumOrders + product.getPrice() < client.getClientLimit();

        Double availableLimit = client.getClientLimit() - sumOrders;
        if(!hasEnoughCreditLimit){
            throw new NotEnoughLimitException(String.format(
                    "Client available limit is %f. The client invoice due date is %d",
                    availableLimit, client.getInvoiceDueDate()
            ));
        }
    }

    private List<OrderClientProduct> findOrdersThisMonthByClient(Long idClient, int invoiceDueDateDay){
        LocalDate today = LocalDate.now();
        LocalDate previousInvoiceDueDate;
        LocalDate nextInvoiceDueDate;

        int previousInvoiceDueDateDay = invoiceDueDateDay;
        int nextInvoiceDueDateDay = invoiceDueDateDay;
        Month thisMonth = today.getMonth();

        if(today.getDayOfMonth() < invoiceDueDateDay){
            if(invoiceDueDateDay > thisMonth.minus(1).length(today.isLeapYear())){
                previousInvoiceDueDateDay = thisMonth.length(today.isLeapYear());
            }

            if(invoiceDueDateDay > thisMonth.length(today.isLeapYear())){
                nextInvoiceDueDateDay = thisMonth.length(today.isLeapYear());
            }

            previousInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    thisMonth.minus(1),
                    previousInvoiceDueDateDay
            );

            nextInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    thisMonth,
                    nextInvoiceDueDateDay
            );
        } else {
            if(invoiceDueDateDay > thisMonth.length(today.isLeapYear())){
                previousInvoiceDueDateDay = thisMonth.length(today.isLeapYear());
            }

            if(invoiceDueDateDay > thisMonth.plus(1).length(today.isLeapYear())){
                nextInvoiceDueDateDay = thisMonth.length(today.isLeapYear());
            }

            previousInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    thisMonth,
                    previousInvoiceDueDateDay
            );

            nextInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    thisMonth.plus(1),
                    nextInvoiceDueDateDay
            );
        }

        return orderClientProductRepository.findByIdClientAndBetweenOrderDate(
                idClient,
                previousInvoiceDueDate,
                nextInvoiceDueDate
        );
    }

    private Double sumOrdersValues(List<OrderClientProduct> listOrdersItems) {
        Double sumValues = 0.0;

        for(int i = 0; i < listOrdersItems.size(); i++){
            sumValues += (listOrdersItems.get(i).getProductAmount() * listOrdersItems.get(i).getProducts().getPrice());
        }

        return sumValues;
    }

    public void deleteProductInOrder(ProductOrderIds productOrderIds) {
        orderClientProductRepository.deleteByOrderAndProduct(
                productOrderIds.getIdOrder(),
                productOrderIds.getIdProduct()
        );
    }

    public void updateProductAmount(AmountProductOrderIds amountProductOrderIds) {
        Optional<OrderClientProduct> orderClientProduct = orderClientProductRepository.findByProductAndOrder(
                amountProductOrderIds.getIdProduct(),
                amountProductOrderIds.getIdOrder()
        );

        if(orderClientProduct.isPresent()){
            orderClientProduct.get().setProductAmount(amountProductOrderIds.getAmount());
            orderClientProductRepository.save(orderClientProduct.get());
        }
    }
}
