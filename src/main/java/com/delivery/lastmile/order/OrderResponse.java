package com.delivery.lastmile.order;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class OrderResponse {
    private Long id;

    private String customerName;

    private String pickupAddress;

    private String deliveryAddress;

    private Double deliveryLatitude;

    private Double deliveryLongitude;

    private OrderStatus status;


}
