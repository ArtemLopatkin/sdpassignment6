package com.company;

import java.math.BigDecimal;
import java.util.Objects;

public final class Bike extends RentalEquipment {
    private final boolean brakesWorking;
    private final boolean tiresInflated;

    public Bike(String code, String name, BigDecimal dailyRate, boolean brakesWorking, boolean tiresInflated) {
        super(code, name, dailyRate);
        this.brakesWorking = brakesWorking;
        this.tiresInflated = tiresInflated;
    }

    public boolean hasWorkingBrakes() {
        return brakesWorking;
    }

    public boolean hasInflatedTires() {
        return tiresInflated;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
        Objects.requireNonNull(visitor, "Equipment visitor is required.");
        visitor.visitBike(this);
    }
}
