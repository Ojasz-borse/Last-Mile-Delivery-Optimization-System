package com.delivery.lastmile.routing;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EtaResponse {

    private double distanceKm;

    private TrafficLevel traffic;

    private double etaMinutes;
}