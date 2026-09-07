package com.delivery.lastmile.deliverypartner;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/delivery-partners")
@RequiredArgsConstructor
public class DeliveryPartnerController
{
    private final DeliveryPartnerService deliveryPartnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryPartnerResponse createDeliveryPartner(@Valid @RequestBody CreateDeliveryPartnerRequest request){
        return deliveryPartnerService.createDelivery(request);

    }

}
