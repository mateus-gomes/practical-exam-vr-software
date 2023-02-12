package br.com.mateusg.practicalexam.handler;

import br.com.mateusg.practicalexam.exception.ExceptionResponse;
import br.com.mateusg.practicalexam.exception.NotADayOfTheMonthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericExceptions(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = buildExceptionResponse(exception, request);

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotADayOfTheMonthException.class)
    public ResponseEntity<ExceptionResponse> handleNotADayOfTheMonthExceptions(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = buildExceptionResponse(exception, request);

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ExceptionResponse buildExceptionResponse(Exception exception, WebRequest request){
        return new ExceptionResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }
}
