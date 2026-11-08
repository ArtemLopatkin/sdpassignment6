package com.company;

public interface EquipmentVisitor {
    void visitBike(Bike bike);

    void visitSkis(Skis skis);

    void visitTent(Tent tent);
}
