package com.delivery.lastmile.routing;

public enum TrafficLevel {

    NORMAL(1.0),
    MODERATE(1.2),
    HEAVY(1.5),
    SEVERE(2.0);

    private final double multiplier;

    TrafficLevel(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}