package com.delivery.lastmile.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private String customerName;

    private String pickupAddress;

    private String deliveryAddress;

    private Double deliveryLatitude;

    private Double deliveryLongitude;
}
