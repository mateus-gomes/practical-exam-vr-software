package br.com.mateusg.practicalexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughLimitException extends RuntimeException {
    private String exception;

    public NotEnoughLimitException(String message) {
        super(message);
        this.exception = message;
    }
}
