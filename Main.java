import java.time.LocalDate;

import model.Cart;
import model.Cheese;
import model.Product;
import model.ScratchCard;
import model.TV;
import service.Checkout;
import service.ShippingService;
import user.Customer;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Test Case 1: Successful Checkout ===");
        testSuccessfulCheckout();

        System.out.println("\n=== Test Case 2: Insufficient Balance ===");
        testInsufficientBalance();

        System.out.println("\n=== Test Case 3: Expired Product ===");
        testExpiredProduct();

        System.out.println("\n=== Test Case 4: Out of Stock ===");
        testOutOfStock();

        System.out.println("\n=== Test Case 5: Empty Cart ===");
        testEmptyCart();
    }

    public static void testSuccessfulCheckout() {
        Customer customer = new Customer("Alice", 1000);
        Cart cart = new Cart();

        Product cheese = new Cheese("Cheese", 100, 5, LocalDate.now().plusDays(5), 0.2);
        Product scratchCard = new ScratchCard("Scratch Card", 50, 10);

        cart.add(cheese, 2);            // 200
        cart.add(scratchCard, 1);       // 50

        ShippingService shipping = new ShippingService();
        try {
            Checkout.process(customer, cart, shipping);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void testInsufficientBalance() {
        Customer customer = new Customer("Bob", 100);
        Cart cart = new Cart();

        Product tv = new TV("TV", 3000, 2, 5.0);
        cart.add(tv, 1);

        ShippingService shipping = new ShippingService();
        try {
            Checkout.process(customer, cart, shipping);
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }

    public static void testExpiredProduct() {
        Customer customer = new Customer("Charlie", 1000);
        Cart cart = new Cart();

        Product cheese = new Cheese("Old Cheese", 100, 3, LocalDate.now().minusDays(1), 0.2); // expired
        try {
            cart.add(cheese, 1);  // should fail here
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }

    public static void testOutOfStock() {
        Customer customer = new Customer("Dana", 1000);
        Cart cart = new Cart();

        Product scratchCard = new ScratchCard("Scratch Card", 50, 2);  // only 2 in stock
        try {
            cart.add(scratchCard, 5);  // request 5, should fail
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }

    public static void testEmptyCart() {
        Customer customer = new Customer("Eve", 1000);
        Cart cart = new Cart();  // no items

        ShippingService shipping = new ShippingService();
        try {
            Checkout.process(customer, cart, shipping);
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }
}
