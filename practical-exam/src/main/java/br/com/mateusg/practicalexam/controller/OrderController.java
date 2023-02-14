package br.com.mateusg.practicalexam.controller;

import br.com.mateusg.practicalexam.enums.OrderStatus;
import br.com.mateusg.practicalexam.handler.ErrorHandler;
import br.com.mateusg.practicalexam.model.Order;
import br.com.mateusg.practicalexam.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private ErrorHandler errorHandler = new ErrorHandler();

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity findAllOrders(
        @RequestParam(value="viewType", required=true) String viewType,
        @RequestParam(value="period", required=false) String period,
        @RequestParam(value="client", required=false) Long idClient,
        @RequestParam(value="product", required=false) Long idProduct,
        @RequestParam(value="orderStatus", required=false) OrderStatus orderStatus
    ){
        return ResponseEntity.status(200).body(orderService.findAllFiltered(viewType, period, idClient, idProduct, orderStatus));
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity findOrderById(@PathVariable Long idOrder){
        Optional<Order> order = orderService.findById(idOrder);

        if(order.isPresent()){
            return new ResponseEntity<>(order, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(order, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity createOrder(@Valid @RequestBody Order order, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(422).body(errorHandler.buildErrorMessage(bindingResult));
        }

        if(orderService.existsById(order.getIdOrder())){
            return ResponseEntity.status(400).body("Order id is already in use.");
        } else {
            orderService.create(order);
            return ResponseEntity.status(201).body(order);
        }
    }
}
