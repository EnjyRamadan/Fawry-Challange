package service;

import interfaces.Shippable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
   public void ship(Map<Shippable, Integer> shippablesWithQty) {
    System.out.println("** Shipment notice **");
    double totalWeight = 0;

    for (Map.Entry<Shippable, Integer> entry : shippablesWithQty.entrySet()) {
        Shippable item = entry.getKey();
        int quantity = entry.getValue();
        double itemWeight = item.getWeight() * quantity;
        System.out.printf("%dx %s\t%.1fg%n", quantity, item.getName(), itemWeight * 1000);
        totalWeight += itemWeight;
    }

    System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
}


    public double calculateShippingFee(Map<Shippable, Integer> shippablesWithQty) {
    return shippablesWithQty.isEmpty() ? 0 : 30.0;
}

}
