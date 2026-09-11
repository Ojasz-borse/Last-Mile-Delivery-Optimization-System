package com.delivery.lastmile.routing;

import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.deliverypartner.DeliveryPartnerRepository;
import com.delivery.lastmile.order.Order;
import com.delivery.lastmile.order.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routing")
@RequiredArgsConstructor
public class RoutingController {

    private final OrderRepository orderRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final EtaService etaService;

    @GetMapping("/eta/{orderId}/{partnerId}")
    public EtaResponse calculateEta(
            @PathVariable Long orderId,
            @PathVariable Long partnerId,
            @RequestParam TrafficLevel traffic) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );

        DeliveryPartner partner =
                deliveryPartnerRepository.findById(partnerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Delivery partner not found"
                                )
                        );

        return etaService.calculateEta(
                order,
                partner,
                traffic
        );
    }
}