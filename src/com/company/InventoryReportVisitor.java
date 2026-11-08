package com.company;

import java.util.ArrayList;
import java.util.List;

public class InventoryReportVisitor implements EquipmentVisitor {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void visitBike(Bike bike) {
        lines.add(describe(bike) + " | Bicycle");
    }

    @Override
    public void visitSkis(Skis skis) {
        lines.add(describe(skis) + " | Ski set");
    }

    @Override
    public void visitTent(Tent tent) {
        lines.add(describe(tent) + " | Tent | capacity: " + tent.getCapacity()
                + " | cleaning fee: " + tent.getCleaningFee() + " KZT");
    }

    public List<String> getLines() {
        return List.copyOf(lines);
    }

    public int getItemCount() {
        return lines.size();
    }

    private String describe(RentalEquipment equipment) {
        return equipment.getCode() + " | " + equipment.getName()
                + " | daily rate: " + equipment.getDailyRate() + " KZT";
    }
}
