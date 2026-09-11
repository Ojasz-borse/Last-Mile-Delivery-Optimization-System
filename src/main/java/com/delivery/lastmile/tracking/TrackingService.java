package com.delivery.lastmile.tracking;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.deliverypartner.DeliveryPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final LocationUpdateRepository locationUpdateRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public LocationUpdate updateLocation(
            Long deliveryPartnerId,
            Double latitude,
            Double longitude) {

        DeliveryPartner partner =
                deliveryPartnerRepository.findById(deliveryPartnerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Delivery partner not found"
                                )
                        );

        // Update current location
        partner.setCurrentLatitude(latitude);
        partner.setCurrentLongitude(longitude);

        deliveryPartnerRepository.save(partner);

        // Store location history
        LocationUpdate locationUpdate = LocationUpdate.builder()
                .deliveryPartnerId(deliveryPartnerId)
                .latitude(latitude)
                .longitude(longitude)
                .timestamp(LocalDateTime.now())
                .build();

        return locationUpdateRepository.save(locationUpdate);
    }

    public List<LocationUpdate> getLocationHistory(
            Long deliveryPartnerId) {

        return locationUpdateRepository
                .findByDeliveryPartnerIdOrderByTimestampDesc(
                        deliveryPartnerId
                );
    }
}