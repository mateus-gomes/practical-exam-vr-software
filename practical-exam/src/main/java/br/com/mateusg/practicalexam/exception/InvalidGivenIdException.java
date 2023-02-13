package br.com.mateusg.practicalexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidGivenIdException extends RuntimeException {
    private String exception;

    public InvalidGivenIdException(String message) {
        super(message);
        this.exception = message;
    }
}
