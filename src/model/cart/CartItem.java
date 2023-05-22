package model.cart;

import model.Menu;

public class CartItem {
    private Menu menu;
    private int quantity;

    public CartItem(Menu menu) {
        this.menu = menu;
        this.quantity = 1;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public double getSubtotal() {
        return menu.getPrice() * quantity;
    }
}
