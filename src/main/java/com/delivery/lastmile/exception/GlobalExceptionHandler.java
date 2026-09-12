package com.delivery.lastmile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderNotFound(
            OrderNotFoundException exception) {

        return Map.of(
                "message",
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return errors;
    }

    @ExceptionHandler(NoAvailableDeliveryPartnerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleNoAvailablePartner(
            NoAvailableDeliveryPartnerException exception) {

        return Map.of(
                "message",
                exception.getMessage()
        );
    }
    @ExceptionHandler(OrderAlreadyAssignedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleOrderAlreadyAssigned(
            OrderAlreadyAssignedException exception) {

        return Map.of(
                "message",
                exception.getMessage()
        );
    }

    @ExceptionHandler(DeliveryPartnerNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDeliveryPartnerNotFound(
            NoAvailableDeliveryPartnerException exception) {

        return Map.of(
                "message",
                exception.getMessage()
        );
    }
}