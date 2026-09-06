package com.delivery.lastmile.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSerivce {
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(CreateOrderRequest request){
       // We convert the incoming DTO into our database entity.
        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .pickupAddress(request.getPickupAddress())
                .deliveryAddress(request.getDeliveryAddress())
                .deliverylongitude(request.getDeliveryLongitude())
                .deliveryLatitude(request.getDeliveryLatitude())
                .status(OrderStatus.CREATED)
                .build();
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .id(savedOrder.getId())
                .customerName(savedOrder.getCustomerName())
                .pickupAddress(savedOrder.getPickupAddress())
                .deliveryAddress(savedOrder.getDeliveryAddress())
                .deliveryLatitude(savedOrder.getDeliveryLatitude())
                .deliveryLongitude(savedOrder.getDeliverylongitude())
                .status(savedOrder.getStatus())
                .build();


    }
}
