package model.user;

import model.cart.Cart;
import model.cart.CartItem;
import model.Menu;

import java.util.List;

public abstract class User {
    private final String userID;
    private double balance;
    private Cart cart;

    public User(String userID, double balance){
        this.userID = userID;
        this.balance = balance;
        this.cart = new Cart();
    }

    public String getUserID() {
        return userID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void addToCart(Menu menu){
        cart.addItem(menu);
    }

    public void removeFromCart(Menu menu) {
        cart.removeItem(menu);
    }

    public void balanceTopup(double balance) {
        this.balance += balance;
    }

    public void checkout() {
        double total = cart.calculateTotal();
        if (balance <= total){
            System.out.println("Saldo kurang");
        } else {
            balance -= total;
            System.out.println("Sukses");
            System.out.println(balance);
        }
    }

    public void viewCartItems() {
        System.out.println("Items in the cart:");
        List<CartItem> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            for (CartItem cartItem : cartItems) {
                Menu menu = cartItem.getMenu();
                int quantity = cartItem.getQuantity();
                double subtotal = cartItem.getSubtotal();
                System.out.println("- " + menu.getMenuName() + " (Quantity: " + quantity + ") - Subtotal: $" + subtotal);
            }
        }
    }

    public void generateReceipt() {}
}
