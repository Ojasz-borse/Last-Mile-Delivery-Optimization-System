package com.delivery.lastmile.routing;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutingService {

    private final DistanceService distanceService;

    public double calculateDeliveryDistance(
            Order order,
            DeliveryPartner partner) {

        return distanceService.calculateDistance(
                order.getDeliveryLatitude(),
                order.getDeliverylongitude(),
                partner.getCurrentLatitude(),
                partner.getCurrentLongitude()
        );
    }
}