package br.com.mateusg.practicalexam.service;

import br.com.mateusg.practicalexam.enums.OrderStatus;
import br.com.mateusg.practicalexam.exception.NotAValidStatusException;
import br.com.mateusg.practicalexam.model.Order;
import br.com.mateusg.practicalexam.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public boolean existsById(Long idOrder) {
        return orderRepository.existsById(idOrder);
    }

    public void create(Order order) {
        verifyIfOrderStatusIsValid(order.getStatus());

        orderRepository.save(order);
    }

    private void verifyIfOrderStatusIsValid(String status){
        if(!OrderStatus.isValidStatus(status)){
            throw new NotAValidStatusException(String.format("Order status should be: %s", OrderStatus.listOfValidStatus()));
        }
    }

    public Optional<Order> findById(Long idOrder) {
        return orderRepository.findById(idOrder);
    }

    public List<Order> findAllFiltered(
            String viewType,
            String orderDate,
            Long idClient,
            Long idProduct,
            OrderStatus orderStatus
    ) {
        return orderRepository.findAllFiltered(idClient, idProduct, orderStatus.toString(), orderDate);
    }
}
