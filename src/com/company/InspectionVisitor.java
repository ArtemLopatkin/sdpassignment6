package com.company;

import java.util.ArrayList;
import java.util.List;

public class InspectionVisitor implements EquipmentVisitor {
    private final List<String> results = new ArrayList<>();
    private int readyCount;

    @Override
    public void visitBike(Bike bike) {
        List<String> problems = new ArrayList<>();
        if (!bike.hasWorkingBrakes()) {
            problems.add("brakes need repair");
        }
        if (!bike.hasInflatedTires()) {
            problems.add("tires need inflation");
        }
        addResult(bike, problems);
    }

    @Override
    public void visitSkis(Skis skis) {
        List<String> problems = new ArrayList<>();
        if (!skis.hasSecureBindings()) {
            problems.add("bindings need adjustment");
        }
        if (!skis.hasIntactEdges()) {
            problems.add("edges need repair");
        }
        addResult(skis, problems);
    }

    @Override
    public void visitTent(Tent tent) {
        List<String> problems = new ArrayList<>();
        if (tent.getAvailablePoles() < tent.getRequiredPoles()) {
            problems.add("missing poles: " + (tent.getRequiredPoles() - tent.getAvailablePoles()));
        }
        if (!tent.hasIntactFabric()) {
            problems.add("fabric needs repair");
        }
        addResult(tent, problems);
    }

    public List<String> getResults() {
        return List.copyOf(results);
    }

    public int getReadyCount() {
        return readyCount;
    }

    public int getServiceCount() {
        return results.size() - readyCount;
    }

    private void addResult(RentalEquipment equipment, List<String> problems) {
        String status;
        if (problems.isEmpty()) {
            readyCount++;
            status = "READY";
        } else {
            status = "NEEDS SERVICE: " + String.join(", ", problems);
        }
        results.add(equipment.getCode() + " | " + equipment.getName() + " | " + status);
    }
}
