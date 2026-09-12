package com.delivery.lastmile.routing;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtaService {

    private final DistanceService distanceService;

    public EtaResponse calculateEta(
            Order order,
            DeliveryPartner partner,
            TrafficLevel trafficLevel) {

        double distance = distanceService.calculateDistance(
                partner.getCurrentLatitude(),
                partner.getCurrentLongitude(),
                order.getDeliveryLatitude(),
                order.getDeliverylongitude()
        );

        double averageSpeed = 30.0;

        double timeInHours = distance / averageSpeed;

        double baseEtaMinutes = timeInHours * 60;

        double finalEtaMinutes =
                baseEtaMinutes * trafficLevel.getMultiplier();

        return EtaResponse.builder()
                .distanceKm(
                        Math.round(distance * 100.0) / 100.0
                )
                .traffic(trafficLevel)
                .etaMinutes(
                        Math.round(finalEtaMinutes * 100.0) / 100.0
                )
                .build();
    }
}