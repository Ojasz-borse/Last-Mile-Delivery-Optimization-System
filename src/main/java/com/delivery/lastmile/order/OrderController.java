package com.delivery.lastmile.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderSerivce orderSerivce;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public OrderResponse createOrder(@RequestBody CreateOrderRequest request){
    return orderSerivce.createOrder(request);
    }

}
