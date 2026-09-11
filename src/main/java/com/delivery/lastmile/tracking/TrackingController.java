package com.delivery.lastmile.tracking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    @PutMapping("/{deliveryPartnerId}/location")
    public LocationUpdate updateLocation(
            @PathVariable Long deliveryPartnerId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        return trackingService.updateLocation(
                deliveryPartnerId,
                latitude,
                longitude
        );
    }

    @GetMapping("/{deliveryPartnerId}/history")
    public List<LocationUpdate> getLocationHistory(
            @PathVariable Long deliveryPartnerId) {

        return trackingService.getLocationHistory(
                deliveryPartnerId
        );
    }
}