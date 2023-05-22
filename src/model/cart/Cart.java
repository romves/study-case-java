package model.cart;

import model.Menu;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<CartItem>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Menu menu) {
        for (CartItem item : items) {
            if (item.getMenu().equals(menu)) {
                item.incrementQuantity();
                return;
            }
        }
        items.add(new CartItem(menu));
    }

    public void removeItem(Menu menu) {
        for (CartItem item : items) {
            if (item.getMenu().equals(menu)) {
                item.decrementQuantity();
                if (item.getQuantity() == 0) {
                    items.remove(menu);
                }
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
    }

    public double calculateTotal(){
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
