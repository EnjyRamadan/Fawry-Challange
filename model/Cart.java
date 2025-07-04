package model;

import interfaces.Shippable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Product not available or expired");
        }
        items.add(new CartItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public List<Shippable> getShippables() {
        return items.stream()
                .map(i -> i.product)
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toList());
    }
    // Add this to Cart.java
public Map<Shippable, Integer> getShippableItemsWithQuantities() {
    Map<Shippable, Integer> shippableItems = new HashMap<>();

    for (CartItem item : items) {
        if (item.product instanceof Shippable) {
            Shippable shippable = (Shippable) item.product;
            shippableItems.put(shippable,
                shippableItems.getOrDefault(shippable, 0) + item.quantity);
        }
    }
    return shippableItems;
}

}
