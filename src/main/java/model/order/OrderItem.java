package model.order;

import model.cart.CartItem;
import model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private LocalDate createdAt;
    private static int objectCount = 0;
    private int orderNumber;
    private double totalPrice = 0;
    private List<CartItem> orderedItem;
    private String appliedPromo;
    private User user;

    public OrderItem(List<CartItem> orderedItem, double totalPrice, String appliedPromo) {
        ++OrderItem.objectCount;
        this.createdAt = LocalDate.now();
        this.orderNumber = objectCount;
        this.orderedItem = new ArrayList<>(orderedItem);
        this.totalPrice = totalPrice;
        this.user = user;
        this.appliedPromo = appliedPromo;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<CartItem> getOrderedItem() {
        return orderedItem;
    }

    public String getAppliedPromo() {
        return appliedPromo;
    }

    public int getQuantity() {
        int counter = 0;
        for (CartItem item : orderedItem) {
            counter += item.getQuantity();
        }
        return counter;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getItemSubTotal(){
        double total = 0.0;
        for (CartItem item : orderedItem) {
            total += item.getSubtotal();
        }
        return total;
    }
}
