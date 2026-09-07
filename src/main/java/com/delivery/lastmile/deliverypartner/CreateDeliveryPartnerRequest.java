package com.delivery.lastmile.deliverypartner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeliveryPartnerRequest {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "PhoneNumber is required")

    private String phoneNumber;
    @NotNull(message = "current Latitude is required")

    private Double currentLatitude;
    @NotNull(message = "current Longitude is required")

    private Double currentLongitude;
}
