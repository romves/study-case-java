package model.user;

import model.cart.Cart;
import model.Menu;
import model.order.Order;

public abstract class User implements Cloneable {
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

    public void setUserName(String userName) {
        this.userName = userName;
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
       if(cart.getListItem().isEmpty()) {
           orderList.getLastOrderDetails();
           return;
       }
        cart.viewAllCartItem();
    }

    public void viewOrderHistory() {
        orderList.getOrderHistory();
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            // Handle the exception, e.g., log it or throw a runtime exception
            return null;
        }
    }
}
