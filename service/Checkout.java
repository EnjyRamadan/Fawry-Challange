package service;

import model.*;
import user.Customer;

import java.util.List;

public class Checkout {
    public static void process(Customer customer, Cart cart, ShippingService shippingService) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        List<CartItem> items = cart.getItems();
        List<interfaces.Shippable> shippables = cart.getShippables();
        double subtotal = cart.getSubtotal();
        double shipping = shippingService.calculateShippingFee(shippables);
        double total = subtotal + shipping;

        if (!customer.canPay(total)) {
            throw new IllegalStateException("Insufficient balance");
        }

        // Update product stock
        for (CartItem item : items) {
            item.product.reduceQuantity(item.quantity);
        }

        // Shipping
        if (!shippables.isEmpty()) {
            shippingService.ship(shippables);
        }

        // Payment
        customer.pay(total);

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.quantity + "x " + item.product.getName() + "\t" + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + shipping);
        System.out.println("Amount\t\t" + total);
        System.out.println("Balance\t\t" + customer.getBalance());
    }
}
