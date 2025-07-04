package model;

import interfaces.Shippable;

public class TV extends Product implements Shippable {
    private double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }

    public double getWeight() {
        return weight;
    }
}
