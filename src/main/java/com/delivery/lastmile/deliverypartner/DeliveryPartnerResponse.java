package com.delivery.lastmile.deliverypartner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class DeliveryPartnerResponse {
    private Long id;

    private String name;

    private String phoneNumber;

    private Double currentLatitude;

    private Double currentLongitude;

    private DeliveryPartnerStatus status;
}
