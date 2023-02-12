package br.com.mateusg.practicalexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NotADayOfTheMonthException extends RuntimeException {
      private String exception;

    public NotADayOfTheMonthException(String message) {
        super(message);
        this.exception = message;
    }
}