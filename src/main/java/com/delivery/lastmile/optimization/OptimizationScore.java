package com.delivery.lastmile.optimization;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OptimizationScore {

    private Long deliveryPartnerId;

    private double distanceScore;

    private double etaScore;

    private double workloadScore;

    private double trafficScore;

    private double totalScore;
}