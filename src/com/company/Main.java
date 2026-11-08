package com.company;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Equipment> equipment = List.of(
                new Bike("B1", "City bike", new BigDecimal("3000.00"), true, true),
                new Skis("S1", "Alpine skis", new BigDecimal("5000.00"), false, true),
                new Tent("T1", "Family tent", new BigDecimal("4000.00"),
                        4, 3, 3, true, new BigDecimal("1500.00"))
        );

        InventoryReportVisitor report = new InventoryReportVisitor();
        visitAll(equipment, report);
        System.out.println("Equipment inventory:");
        report.getLines().forEach(System.out::println);
        System.out.println("Total items: " + report.getItemCount());

        int rentalDays = 4;
        RentalCostVisitor rentalCost = new RentalCostVisitor(rentalDays);
        visitAll(equipment, rentalCost);
        System.out.println("\nRental estimates for " + rentalDays + " days:");
        rentalCost.getQuotes().forEach(System.out::println);
        System.out.println("Total estimated cost: " + rentalCost.getTotalCost() + " KZT");

        InspectionVisitor inspection = new InspectionVisitor();
        visitAll(equipment, inspection);
        System.out.println("\nInspection results:");
        inspection.getResults().forEach(System.out::println);
        System.out.println("Ready items: " + inspection.getReadyCount());
        System.out.println("Items needing service: " + inspection.getServiceCount());
    }

    private static void visitAll(List<Equipment> equipment, EquipmentVisitor visitor) {
        for (Equipment item : equipment) {
            item.accept(visitor);
        }
    }
}
