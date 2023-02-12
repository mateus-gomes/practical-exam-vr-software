package br.com.mateusg.practicalexam.controller;

import br.com.mateusg.practicalexam.handler.ErrorHandler;
import br.com.mateusg.practicalexam.model.Order;
import br.com.mateusg.practicalexam.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private ErrorHandler errorHandler = new ErrorHandler();

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity findAllOrders(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
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
