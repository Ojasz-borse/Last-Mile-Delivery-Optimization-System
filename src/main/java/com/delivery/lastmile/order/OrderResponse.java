package com.delivery.lastmile.order;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
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

    private Long deliveryPartnerId;

    

}
