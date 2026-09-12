package com.delivery.lastmile.deliverypartner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLocationRequest {
@NotNull(message = "Latitude is needed")
    private Double latitude;

    @NotNull(message = "Longitude is needed")

    private Double longitude;
}
