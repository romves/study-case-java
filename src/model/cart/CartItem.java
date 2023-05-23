package model.cart;

import model.Menu;

public class CartItem {
    private Menu menu;
    private int quantity;

    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity(int quantity) {
       this.quantity += quantity;
    }

    public void decrementQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return menu.getPrice() * quantity;
    }
}
