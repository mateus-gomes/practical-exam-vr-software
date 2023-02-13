package br.com.mateusg.practicalexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductAlreadyInOrderException extends RuntimeException {
    private String exception;

    public ProductAlreadyInOrderException(String message) {
        super(message);
        this.exception = message;
    }
}
