package br.com.mateusg.practicalexam.service;

import br.com.mateusg.practicalexam.exception.InvalidGivenIdException;
import br.com.mateusg.practicalexam.exception.ProductAlreadyInOrderException;
import br.com.mateusg.practicalexam.model.Client;
import br.com.mateusg.practicalexam.model.Order;
import br.com.mateusg.practicalexam.model.OrderClientProduct;
import br.com.mateusg.practicalexam.repository.OrderClientProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        executeIsCreditLimitEnoughValidation(idClient);
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

    private void executeIsCreditLimitEnoughValidation(Long idClient){
        Client client = clientService.findById(idClient).get();

        findOrdersThisMonthByClient(client.getIdClient(), client.getInvoiceDueDate());
    }

    private Order findOrdersThisMonthByClient(Long idClient, int invoiceDueDate){
        LocalDate today = LocalDate.now();
        LocalDate previousInvoiceDueDate;
        LocalDate nextInvoiceDueDate;

        if(today.getDayOfMonth() < invoiceDueDate){
            previousInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    today.getMonth().minus(1),
                    invoiceDueDate
            );

            nextInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    today.getMonth(),
                    invoiceDueDate
            );
        } else {
            previousInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    today.getMonth(),
                    invoiceDueDate
            );

            nextInvoiceDueDate = LocalDate.of(
                    today.getYear(),
                    today.getMonth().plus(1),
                    invoiceDueDate
            );
        }

        //find where client id = idClient and orderDate between invoiceDueDate/month and invoiceDueDate/nextMonth;
        return findByIdClientAndBetween
    }
}
