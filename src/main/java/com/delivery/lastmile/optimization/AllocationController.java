package com.delivery.lastmile.optimization;

import com.delivery.lastmile.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optimization")
@RequiredArgsConstructor
public class AllocationController {

    private final AllocationService allocationService;

    @PostMapping("/assign/{orderId}")
    public OrderResponse assignOrder(@PathVariable Long orderId) {

        return allocationService.assignOrder(orderId);
    }
}