package com.delivery.lastmile.exception;

public class NoAvailableDeliveryPartnerException
        extends RuntimeException {

    public NoAvailableDeliveryPartnerException(String message) {
        super(message);
    }
}