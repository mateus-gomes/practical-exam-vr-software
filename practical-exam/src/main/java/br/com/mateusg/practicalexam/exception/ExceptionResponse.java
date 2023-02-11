package br.com.mateusg.practicalexam.exception;

import java.time.LocalDate;
import java.util.Date;

public class ExceptionResponse {
    private LocalDate timestamp;
    private String message;
    private String details;

    public ExceptionResponse(LocalDate timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
