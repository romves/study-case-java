package model.user;

import model.cart.Cart;
import model.Menu;
import model.order.Order;

import java.util.List;

public abstract class User {
    private final String userID;
    private double balance;
    private Cart cart;
    private Order orderList;
    String userName;

    public User(String userID, double balance){
        this.userID = userID;
        this.userName = "Guest";
        this.balance = balance;
        this.orderList = new Order(this);
        this.cart = new Cart(this, this.orderList);
    }

    public String getUserName() {
        return userName;
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

    public double getShippingCost() {
        return 15_000;
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Menu menu, int quantity){
        cart.addItem(menu, quantity);
    }

    public void removeFromCart(Menu menu, int quantity) {
        cart.removeItem(menu, quantity);
    }

    public void balanceTopup(double balance) {
        double before = 0;
        before += this.balance;
        this.balance += balance;
        System.out.printf("%s %s %s %s %s\n","TOPUP SUCCESS:", getUserName(), before , "=>", this.balance);
    }

    public void checkout() {
        cart.checkoutCart();
    }

    public void viewCartItems() {
        cart.viewAllCartItem();
    }

    public void viewOrderHistory() {
        orderList.getOrderHistory();
    }

    public void viewLastOrderDetails() {
        orderList.getLastOrderDetails();
    }
}
