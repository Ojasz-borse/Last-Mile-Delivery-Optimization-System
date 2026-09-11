package com.delivery.lastmile.exception;

public class DeliveryPartnerNotFoundException
    extends RuntimeException {

    public DeliveryPartnerNotFoundException(String message) {
            super(message);
        }
}
