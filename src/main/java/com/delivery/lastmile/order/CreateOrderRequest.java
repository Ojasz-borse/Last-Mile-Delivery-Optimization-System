package com.delivery.lastmile.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    @NotBlank(message = "Customer name is required")
    private String customerName;
    @NotBlank(message = "Pickup address is required")

    private String pickupAddress;
    @NotBlank(message = "Delivery address is required")

    private String deliveryAddress;
    @NotNull(message = "Delivery latitude is required")

    private Double deliveryLatitude;
    @NotNull(message = "Delivery longitude is required")
    private Double deliveryLongitude;
}
