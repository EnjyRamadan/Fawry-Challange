package model;

public class ScratchCard extends Product {
    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }
}
