package com.delivery.lastmile.deliverypartner;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping

    public ResponseEntity<List<DeliveryPartnerResponse>> getAllDeliver(){
        List<DeliveryPartnerResponse> response = deliveryPartnerService.getAllPartner();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @GetMapping("/{id}")
    public DeliveryPartnerResponse getDeliveryPartnerById(
            @PathVariable Long id) {

        return deliveryPartnerService.getDeliveryPartnerById(id);
    }
  @PutMapping("/{id}/location")
    public DeliveryPartnerResponse updateLocation(@PathVariable Long id ,@Valid @RequestBody UpdateLocationRequest request )
  {
      return deliveryPartnerService.updateLocation(id, request);

  }
}
