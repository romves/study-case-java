package model.order;

import model.cart.Cart;
import model.cart.CartItem;
import model.promo.PromoCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private LocalDate createdAt;
    private static int orderNumber = 0;
    private double subTotal = 0;
    private double totalPrice = 0;
    private List<CartItem> orderedItem;

    public OrderItem(List<CartItem> orderedItem) {
        ++this.orderNumber;
        this.createdAt = LocalDate.now();
        this.orderedItem = new ArrayList<>(orderedItem);
    }

    public static int getOrderNumber() {
        return orderNumber;
    }

    public List<CartItem> getOrderedItem() {
        return orderedItem;
    }

    public int getQuantity() {
        int counter = 0;
        for (CartItem item : orderedItem) {
            counter += item.getQuantity();
        }
        return counter;
    }

    public double getItemSubTotal(){
        double total = 0.0;
        for (CartItem item : orderedItem) {
            total += item.getSubtotal();
        }
        return total;
    }

}
