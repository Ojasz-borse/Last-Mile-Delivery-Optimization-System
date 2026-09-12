package com.delivery.lastmile.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderSerivce orderSerivce;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request){
    return orderSerivce.createOrder(request);
    }
    @GetMapping
    public List<OrderResponse>getAllOrders(){
        return orderSerivce.getAllOrders();
    }
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        return orderSerivce.getOrderById(id);
    }
}
