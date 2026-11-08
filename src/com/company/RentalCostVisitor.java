package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RentalCostVisitor implements EquipmentVisitor {
    private static final int BIKE_DISCOUNT_DAYS = 7;
    private static final int SKI_DISCOUNT_DAYS = 3;
    private static final BigDecimal BIKE_PRICE_FACTOR = new BigDecimal("0.90");
    private static final BigDecimal SKI_PRICE_FACTOR = new BigDecimal("0.85");

    private final int rentalDays;
    private final List<String> quotes = new ArrayList<>();
    private BigDecimal totalCost = new BigDecimal("0.00");

    public RentalCostVisitor(int rentalDays) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental duration must be at least one day.");
        }
        this.rentalDays = rentalDays;
    }

    @Override
    public void visitBike(Bike bike) {
        BigDecimal cost = baseCost(bike);
        if (rentalDays >= BIKE_DISCOUNT_DAYS) {
            cost = cost.multiply(BIKE_PRICE_FACTOR);
        }
        addQuote(bike, cost);
    }

    @Override
    public void visitSkis(Skis skis) {
        BigDecimal cost = baseCost(skis);
        if (rentalDays >= SKI_DISCOUNT_DAYS) {
            cost = cost.multiply(SKI_PRICE_FACTOR);
        }
        addQuote(skis, cost);
    }

    @Override
    public void visitTent(Tent tent) {
        addQuote(tent, baseCost(tent).add(tent.getCleaningFee()));
    }

    public List<String> getQuotes() {
        return List.copyOf(quotes);
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    private BigDecimal baseCost(RentalEquipment equipment) {
        return equipment.getDailyRate().multiply(BigDecimal.valueOf(rentalDays));
    }

    private void addQuote(RentalEquipment equipment, BigDecimal cost) {
        BigDecimal roundedCost = cost.setScale(2, RoundingMode.HALF_UP);
        quotes.add(equipment.getCode() + " | " + equipment.getName() + " | " + roundedCost + " KZT");
        totalCost = totalCost.add(roundedCost);
    }
}
