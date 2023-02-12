package br.com.mateusg.practicalexam.controller;

import br.com.mateusg.practicalexam.handler.ErrorHandler;
import br.com.mateusg.practicalexam.model.Product;
import br.com.mateusg.practicalexam.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ErrorHandler errorHandler = new ErrorHandler();

    @Autowired
    private ProductService productService;

    @GetMapping
    private ResponseEntity findAllProducts(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(422).body(errorHandler.buildErrorMessage(bindingResult));
        }

        if(productService.existsById(product.getIdProduct())){
            return ResponseEntity.status(400).body("Product id is already in use.");
        } else {
            productService.create(product);
            return ResponseEntity.status(201).body(product);
        }
    }
}
