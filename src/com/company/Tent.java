package com.company;

import java.math.BigDecimal;
import java.util.Objects;

public final class Tent extends RentalEquipment {
    private final int capacity;
    private final int requiredPoles;
    private final int availablePoles;
    private final boolean fabricIntact;
    private final BigDecimal cleaningFee;

    public Tent(String code, String name, BigDecimal dailyRate, int capacity, int requiredPoles,
                int availablePoles, boolean fabricIntact, BigDecimal cleaningFee) {
        super(code, name, dailyRate);
        if (capacity < 1 || requiredPoles < 1 || availablePoles < 0) {
            throw new IllegalArgumentException("Capacity and required poles must be positive; available poles cannot be negative.");
        }
        this.capacity = capacity;
        this.requiredPoles = requiredPoles;
        this.availablePoles = availablePoles;
        this.fabricIntact = fabricIntact;
        this.cleaningFee = Amounts.nonNegative(cleaningFee, "Cleaning fee");
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRequiredPoles() {
        return requiredPoles;
    }

    public int getAvailablePoles() {
        return availablePoles;
    }

    public boolean hasIntactFabric() {
        return fabricIntact;
    }

    public BigDecimal getCleaningFee() {
        return cleaningFee;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
        Objects.requireNonNull(visitor, "Equipment visitor is required.");
        visitor.visitTent(this);
    }
}
