package com.company;

import java.math.BigDecimal;
import java.util.Objects;

public final class Skis extends RentalEquipment {
    private final boolean bindingsSecure;
    private final boolean edgesIntact;

    public Skis(String code, String name, BigDecimal dailyRate, boolean bindingsSecure, boolean edgesIntact) {
        super(code, name, dailyRate);
        this.bindingsSecure = bindingsSecure;
        this.edgesIntact = edgesIntact;
    }

    public boolean hasSecureBindings() {
        return bindingsSecure;
    }

    public boolean hasIntactEdges() {
        return edgesIntact;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
        Objects.requireNonNull(visitor, "Equipment visitor is required.");
        visitor.visitSkis(this);
    }
}
