package br.com.mateusg.practicalexam.controller;

import br.com.mateusg.practicalexam.exception.InvalidGivenIdException;
import br.com.mateusg.practicalexam.handler.ErrorHandler;
import br.com.mateusg.practicalexam.model.OrderClientProduct;
import br.com.mateusg.practicalexam.service.ClientService;
import br.com.mateusg.practicalexam.service.OrderClientProductService;
import br.com.mateusg.practicalexam.service.OrderService;
import br.com.mateusg.practicalexam.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders/items")
public class OrderClientProductController {

    private ErrorHandler errorHandler = new ErrorHandler();

    @Autowired
    private OrderClientProductService orderClientProductService;

    @GetMapping
    public ResponseEntity findAllOrderItems(){
        return new ResponseEntity<>(orderClientProductService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createOrderItem(
            @Valid @RequestBody OrderClientProduct orderClientProduct,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(422).body(errorHandler.buildErrorMessage(bindingResult));
        }

        //orderClientProductService.create(orderClientProduct);
        return ResponseEntity.status(201).body();
    }


}
