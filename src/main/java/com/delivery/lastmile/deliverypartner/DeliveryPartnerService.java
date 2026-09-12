package com.delivery.lastmile.deliverypartner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class DeliveryPartnerService {
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartnerResponse createDelivery(CreateDeliveryPartnerRequest request) {
        DeliveryPartner deliveryPartner = DeliveryPartner.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .currentLatitude(request.getCurrentLatitude())
                .currentLongitude(request.getCurrentLongitude())
                .status(DeliveryPartnerStatus.AVAILABLE)
                .build();
        DeliveryPartner savedPartner = deliveryPartnerRepository.save(deliveryPartner);

        return toResponse(savedPartner);
    }

    private DeliveryPartnerResponse toResponse(DeliveryPartner partner) {
        return DeliveryPartnerResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .phoneNumber(partner.getPhoneNumber())
                .currentLatitude(partner.getCurrentLatitude())
                .currentLongitude(partner.getCurrentLongitude())
                .status(partner.getStatus())
                .build();
    }


    public List<DeliveryPartnerResponse> getAllPartner() {
//        return deliveryPartnerRepository.findAll()
//                .stream()
//                .map(this::toResponse)
//                .toList();
        List<DeliveryPartner> all = deliveryPartnerRepository.findAll();
        List<DeliveryPartnerResponse> partnerResponseList= new ArrayList<>();
        for (DeliveryPartner partner: all){
            partnerResponseList.add(toResponse(partner));
        }
        return partnerResponseList;
    }




    public DeliveryPartnerResponse getDeliveryPartnerById(Long id) {

        DeliveryPartner partner = deliveryPartnerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Delivery partner not found")
                );

        return toResponse(partner);
    }

    public DeliveryPartnerResponse updateLocation(Long id, UpdateLocationRequest request){
        DeliveryPartner partner =
                deliveryPartnerRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Delivery partner not found"
                                )
                        );
partner.setCurrentLatitude(request.getLatitude());
partner.setCurrentLongitude(request.getLongitude());
DeliveryPartner savedPartner = deliveryPartnerRepository.save(partner);
return  toResponse(savedPartner);
    }

}

