package com.company;

import java.math.BigDecimal;

public abstract class RentalEquipment implements Equipment {
    private final String code;
    private final String name;
    private final BigDecimal dailyRate;

    protected RentalEquipment(String code, String name, BigDecimal dailyRate) {
        if (code == null || code.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment code and name are required.");
        }
        this.code = code.trim();
        this.name = name.trim();
        this.dailyRate = Amounts.positive(dailyRate, "Daily rate");
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }
}
