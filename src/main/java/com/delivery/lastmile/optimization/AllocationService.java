package com.delivery.lastmile.optimization;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.deliverypartner.DeliveryPartnerRepository;
import com.delivery.lastmile.deliverypartner.DeliveryPartnerStatus;
import com.delivery.lastmile.exception.NoAvailableDeliveryPartnerException;
import com.delivery.lastmile.exception.OrderAlreadyAssignedException;
import com.delivery.lastmile.exception.OrderNotFoundException;
import com.delivery.lastmile.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationService {

    private final OrderRepository orderRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final OrderSerivce orderService;

    public OrderResponse assignOrder(Long orderId) {

        // 1. Find the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order with id " + orderId + " not found"
                        )
                );

        // 2. Check order status BEFORE looking for partners
        if (order.getStatus() != OrderStatus.CREATED) {
            throw new OrderAlreadyAssignedException(
                    "Order with id " + orderId +
                            " is already assigned or processed"
            );
        }

        // 3. Find available partners
        List<DeliveryPartner> availablePartners =
                deliveryPartnerRepository.findByStatus(
                        DeliveryPartnerStatus.AVAILABLE
                );

        // 4. No partners available
        if (availablePartners.isEmpty()) {
            throw new NoAvailableDeliveryPartnerException(
                    "No delivery partner available"
            );
        }

        // 5. Find nearest partner
        DeliveryPartner nearestPartner =
                findNearestPartner(order, availablePartners);

        // 6. Assign partner to order
        order.setDeliveryPartnerId(nearestPartner.getId());

        // 7. Change order status
        order.setStatus(OrderStatus.ASSIGNED);

        // 8. Make partner busy
        nearestPartner.setStatus(DeliveryPartnerStatus.BUSY);

        // 9. Save partner
        deliveryPartnerRepository.save(nearestPartner);

        // 10. Save order
        Order savedOrder = orderRepository.save(order);

        // 11. Return DTO
        return orderService.toResponse(savedOrder);
    }

    private DeliveryPartner findNearestPartner(
            Order order,
            List<DeliveryPartner> partners) {

        DeliveryPartner nearestPartner = null;

        double shortestDistance = Double.MAX_VALUE;

        for (DeliveryPartner partner : partners) {

            double distance = calculateDistance(
                    order.getDeliveryLatitude(),
                    order.getDeliverylongitude(),
                    partner.getCurrentLatitude(),
                    partner.getCurrentLongitude()
            );

            if (distance < shortestDistance) {

                shortestDistance = distance;
                nearestPartner = partner;
            }
        }

        return nearestPartner;
    }

    private double calculateDistance(
            double latitude1,
            double longitude1,
            double latitude2,
            double longitude2) {

        double earthRadius = 6371;

        double latDistance =
                Math.toRadians(latitude2 - latitude1);

        double lonDistance =
                Math.toRadians(longitude2 - longitude1);

        double a =
                Math.sin(latDistance / 2)
                        * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(latitude1))
                        * Math.cos(Math.toRadians(latitude2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return earthRadius * c;
    }
}