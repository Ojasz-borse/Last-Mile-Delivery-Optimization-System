package com.delivery.lastmile.optimization;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.order.Order;
import com.delivery.lastmile.routing.DistanceService;
import com.delivery.lastmile.routing.EtaService;
import com.delivery.lastmile.routing.TrafficLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptimizationService {

    private final DistanceService distanceService;
    private final EtaService etaService;

    public DeliveryPartner findBestPartner(
            Order order,
            List<DeliveryPartner> partners,
            TrafficLevel trafficLevel) {

        DeliveryPartner bestPartner = null;

        double bestScore = Double.MAX_VALUE;

        for (DeliveryPartner partner : partners) {

            double distance =
                    distanceService.calculateDistance(
                            partner.getCurrentLatitude(),
                            partner.getCurrentLongitude(),
                            order.getDeliveryLatitude(),
                            order.getDeliverylongitude()
                    );

            double eta =
                    etaService.calculateEta(
                            order,
                            partner,
                            trafficLevel
                    ).getEtaMinutes();

            double score =
                    calculateScore(
                            distance,
                            eta,
                            0,
                            trafficLevel
                    );

            if (score < bestScore) {

                bestScore = score;

                bestPartner = partner;
            }
        }

        return bestPartner;
    }

    private double calculateScore(
            double distance,
            double eta,
            double workload,
            TrafficLevel trafficLevel) {

        double distanceWeight = 0.40;
        double etaWeight = 0.35;
        double workloadWeight = 0.15;
        double trafficWeight = 0.10;

        double trafficScore =
                trafficLevel.getMultiplier();

        return
                (distance * distanceWeight)
                        + (eta * etaWeight)
                        + (workload * workloadWeight)
                        + (trafficScore * trafficWeight);
    }
}