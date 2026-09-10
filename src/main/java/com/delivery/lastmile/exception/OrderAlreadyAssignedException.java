package com.delivery.lastmile.exception;

public class OrderAlreadyAssignedException extends RuntimeException {

    public OrderAlreadyAssignedException(String message) {
        super(message);
    }
}