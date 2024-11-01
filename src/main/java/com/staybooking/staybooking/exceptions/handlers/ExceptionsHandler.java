package com.staybooking.staybooking.exceptions.handlers;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        final BindingResult result = ex.getBindingResult();

        String error = result.getAllErrors().stream().map(e -> {
            if (e instanceof FieldError) {
                return ((FieldError) e).getField() + " : " + e.getDefaultMessage();
            } else {
                return e.getObjectName() + " : " + e.getDefaultMessage();
            }
        }).collect(Collectors.joining(", "));
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.BAD_REQUEST, "4003", error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={EmailAlreadyUsedException.class})
    protected ResponseEntity<APIResponse> handleEmailAlreadyUsed(RuntimeException ex, WebRequest request){
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.BAD_REQUEST, "4001", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={PhoneNumberAlreadyUsedException.class})
    protected ResponseEntity<APIResponse> handlePhoneNumberAlreadyUsed(RuntimeException ex, WebRequest request){
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.BAD_REQUEST, "4002", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={EntityNotFoundException.class})
    protected ResponseEntity<APIResponse> entityNotFoundException(RuntimeException ex, WebRequest request){
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.NOT_FOUND, "4004", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={UnavailabilityPeriodAlreadyDefined.class})
    protected ResponseEntity<APIResponse> handleUnavailabilityPeriodAlreadyDefined(RuntimeException ex, WebRequest request){
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.BAD_REQUEST, "4005", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={OverlappingPriceDateRangeException.class})
    protected ResponseEntity<APIResponse> handleOverlappingPriceDateRangeException(RuntimeException ex, WebRequest request){
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.BAD_REQUEST, "4006", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={OverlappingReservationDateRangeException.class})
    protected ResponseEntity<APIResponse> handleOverlappingReservationDateRangeException(RuntimeException ex, WebRequest request){
        return new ResponseEntity(APIResponse.generateApiResponse(null, HttpStatus.BAD_REQUEST, "4007", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
