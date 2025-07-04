package service;

import interfaces.Shippable;

import java.util.List;

public class ShippingService {
    public void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Shippable item : items) {
            System.out.println(item.getName() + "\t" + item.getWeight() * 1000 + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg\n");
    }

    public double calculateShippingFee(List<Shippable> items) {
        return items.isEmpty() ? 0 : 30.0;
    }
}
