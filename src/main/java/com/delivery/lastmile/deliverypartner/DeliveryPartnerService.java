package com.delivery.lastmile.deliverypartner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryPartnerService {
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartnerResponse createDelivery(CreateDeliveryPartnerRequest request){
            DeliveryPartner deliveryPartner = DeliveryPartner.builder()
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .currentLatitude(request.getCurrentLatitude())
                    .currentLongitude(request.getCurrentLongitude())
                    .status(DeliveryPartnerStatus.AVAILABLE)
                    .build();
            DeliveryPartner savedPartner= deliveryPartnerRepository.save(deliveryPartner);

            return toResponse(savedPartner);
    }
private DeliveryPartnerResponse toResponse(DeliveryPartner partner){
        return DeliveryPartnerResponse.builder()
                .id(partner.getId())
            .name(partner.getName())
            .phoneNumber(partner.getPhoneNumber())
            .currentLatitude(partner.getCurrentLatitude())
            .currentLongitude(partner.getCurrentLongitude())
            .status(partner.getStatus())
            .build();
}



}
