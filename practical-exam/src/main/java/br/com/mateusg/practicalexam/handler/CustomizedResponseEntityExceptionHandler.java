package br.com.mateusg.practicalexam.handler;

import br.com.mateusg.practicalexam.exception.*;
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

    @ExceptionHandler({NotADayOfTheMonthException.class, NotAValidStatusException.class})
    public ResponseEntity<ExceptionResponse> handleNotADayOfTheMonthExceptions(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = buildExceptionResponse(exception, request);

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidGivenIdException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidGivenIdExceptions(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = buildExceptionResponse(exception, request);

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductAlreadyInOrderException.class, NotEnoughLimitException.class})
    public ResponseEntity<ExceptionResponse> handleProductAlreadyInOrderExceptions(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = buildExceptionResponse(exception, request);

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse buildExceptionResponse(Exception exception, WebRequest request){
        return new ExceptionResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }
}
