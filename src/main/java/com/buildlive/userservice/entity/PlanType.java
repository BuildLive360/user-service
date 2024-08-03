package com.buildlive.userservice.entity;

public enum PlanType {
    BUSINESS(12),
    INTERMEDIATE(6),
    BASIC(1);

    private final int durationInMonths;

    PlanType(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public int getDurationInMonths() {
        return this.durationInMonths;
    }
}
