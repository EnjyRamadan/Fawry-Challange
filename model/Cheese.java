package model;

import interfaces.Expirable;
import interfaces.Shippable;

import java.time.LocalDate;

public class Cheese extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public Cheese(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty && !isExpired();
    }

    public double getWeight() {
        return weight;
    }
}
