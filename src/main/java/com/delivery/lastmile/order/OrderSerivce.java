package com.delivery.lastmile.order;

import com.delivery.lastmile.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<OrderResponse> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id" + id + "not found"));

        return toResponse( order);
}

    private OrderResponse toResponse(Order order){
       return OrderResponse.builder()
               .id(order.getId())
               .id(order.getId())
               .customerName(order.getCustomerName())
               .pickupAddress(order.getPickupAddress())
               .deliveryAddress(order.getDeliveryAddress())
               .deliveryLatitude(order.getDeliveryLatitude())
               .deliveryLongitude(order.getDeliverylongitude())
               .status(order.getStatus())
               .build();



    }
}
