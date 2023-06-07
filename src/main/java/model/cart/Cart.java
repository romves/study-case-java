package model.cart;

import model.Menu;
import model.order.Order;
import model.promo.CashbackPromo;
import model.promo.DeliveryPromo;
import model.promo.DiscountPromo;
import model.user.Member;
import model.user.User;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private Order orderList;
    private User user;

    public Cart(User user, Order orderList) {
        this.user = user;
        this.items = new ArrayList<CartItem>();
        this.orderList = orderList;
    }

    public List<CartItem> getListItem() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public void addItem(Menu menu, int quantity) {
        for (CartItem item : items) {
            if (item.getMenu().equals(menu)) {
                System.out.printf("%s %s %s %s\n","ADD_TO_CART SUCCESS:", quantity, menu.getMenuName(), "IS INCREMENTED");
                item.incrementQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(menu, quantity));
        System.out.printf("%s %s %s %s\n","ADD_TO_CART SUCCESS:", quantity, menu.getMenuName(), "IS ADDED");
    }

    public void removeItem(Menu menu, int quantity) {
        for (CartItem item : items) {
            if (item.getMenu().equals(menu) && item.getQuantity() >= quantity) {
                System.out.printf("%s %s %s\n", item.getQuantity(), menu.getMenuName(), "IS DECREMENTED");
                item.decrementQuantity(quantity);
                if (item.getQuantity() <= 0) {
                    items.remove(item);
                    System.out.printf("%s %s\n", menu.getMenuName(), "IS REMOVED");
                }
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
    }

    public void checkoutCart() {
        if (user.getBalance() <= getTotalPrice()){
            System.out.printf("%s: %s %s %s\n","CHECK_OUT FAILED", user.getUserID(), user.getUserName(),"INSUFFICIENT BALANCE");
        } else {
            if (user instanceof Member) {
                if (((Member) user).getUserPromo() instanceof CashbackPromo) {
                    user.setBalance(user.getBalance() - getTotalPrice() + ((Member) user).getUserPromo().getPricePromo(getItemSubTotal()));
                }
                user.setBalance(user.getBalance() - getTotalPrice());
            } else {
                user.setBalance(user.getBalance() - getTotalPrice());
            }
            orderList.handleCheckout(this);
            clearCart();
            if (user instanceof Member) {
                ((Member) user).clearPromoCode();
            }
            System.out.printf("%s: %s %s\n","CHECK_OUT SUCCESS", user.getUserID(), user.getUserName());
        }
    }

    public double getItemSubTotal(){
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double getTotalPrice() {
        double total = 0.0;
        if (user instanceof Member ) {
            if (((Member) user).getUserPromo() instanceof DiscountPromo) {
                return getItemSubTotal() + user.getShippingCost() - ((Member) user).getUserPromo().getPricePromo(getItemSubTotal());
            }
            if (((Member) user).getUserPromo() instanceof DeliveryPromo) {
                return getItemSubTotal() + user.getShippingCost() - ((Member) user).getUserPromo().getPricePromo(getItemSubTotal());
            }
        }
        total = getItemSubTotal() + user.getShippingCost() ;
        return total;
    }

    public void viewAllCartItem() {
        System.out.println();
        System.out.println("Kode Pelanggan: " + user.getUserID());
        System.out.println("Nama: " + user.getUserName());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal");
        System.out.println("==================================================");
        int i=1;
        for (CartItem cartItem : items) {
            Menu menu = cartItem.getMenu();
            String menuName = menu.getMenuName().length() >= 20 ? menu.getMenuName().substring(0, 20) : menu.getMenuName();
            String subtotal = formatter.format(menu.getPrice()*cartItem.getQuantity());
            System.out.printf("%3d | %-20s | %3d | %8s \n", i, menuName, cartItem.getQuantity(), subtotal);
            i++;
        }
        System.out.println("==================================================");
        String subtotal = formatter.format(getItemSubTotal()); //subtotal
        String delivery = formatter.format(user.getShippingCost()); //biaya ongkir
        String total = formatter.format(getTotalPrice()); //total harga
        String balance = formatter.format(user.getBalance()); //saldo
        System.out.printf("%-27s: %9s\n", "Total",subtotal);
        if (user instanceof Member ) {
            if (((Member) user).getUserPromo() != null) {
                if (((Member) user).getUserPromo() instanceof DiscountPromo) {
                    String discount = formatter.format(-((Member) user).getUserPromo().getPricePromo(getItemSubTotal()));
                    System.out.printf("%-27s: %9s\n", "PROMO: " + ((Member) user).getUserPromo().getPromoCode(), discount);
                }
            }
        }
        System.out.printf("%-27s: %9s\n", "Ongkos kirim", delivery);
        if (user instanceof Member) {
            if (((Member) user).getUserPromo() != null){
                if (((Member) user).getUserPromo() instanceof DeliveryPromo){
                    String deliveryOff = formatter.format(-((Member) user).getUserPromo().getPricePromo(getItemSubTotal()));
                    System.out.printf("%-27s: %9s\n", "PROMO: " + ((Member) user).getUserPromo().getPromoCode(), deliveryOff);
                }
            }
        }
        System.out.println("==================================================");
        System.out.printf("%-27s: %9s\n", "Total", total);
        if (user instanceof Member) {
            if (((Member) user).getUserPromo() != null){
                if (((Member) user).getUserPromo() instanceof CashbackPromo){
                    String cashback = formatter.format(((Member) user).getUserPromo().getPricePromo(getItemSubTotal()));
                    System.out.printf("%-27s: %9s\n", "PROMO: " + ((Member) user).getUserPromo().getPromoCode(), cashback);
                }
            }
        }
        System.out.printf("%-27s: %9s\n", "Saldo", balance); //shopping cart Saldo bukan Sisa saldo
        System.out.println();
    }
}
