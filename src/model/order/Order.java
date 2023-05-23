package model.order;

import model.Menu;
import model.cart.Cart;
import model.cart.CartItem;
import model.promo.CashbackPromo;
import model.promo.DeliveryPromo;
import model.promo.DiscountPromo;
import model.user.Member;
import model.user.User;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orders;
    private User user;

    public Order(User user){
        this.user = user;
        this.orders = new ArrayList<OrderItem>();
    }

    public void handleCheckout(Cart cart) {
        String appliedPromo = "";
        if (user instanceof Member) {
            if (((Member) user).getUserPromo() != null) {
                appliedPromo = ((Member) user).getUserPromo().getPromoCode();
            }
        } else {
            appliedPromo = "";
        }
        orders.add(new OrderItem(cart.getListItem(), cart.getTotalPrice(), appliedPromo));
    }

    public void getOrderHistory() {
        System.out.println();
        System.out.println("Kode Pelanggan: " + user.getUserID());
        System.out.println("Nama: " + user.getUserName());
        System.out.println("Saldo: "+ user.getBalance());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        System.out.printf("%3s | %-13s | %6s | %8s | %5s \n", "No", "Nomor Pesanan" , "Jumlah", "Subtotal", "PROMO");
        System.out.println("==================================================");
        int i=1;
        for (OrderItem orderItem : orders) {
//            String subtotal = formatter.format(menu.getPrice()*cartItem.getQuantity());
            System.out.printf("%3d | %13d | %6d | %8s | %5s \n", i, orderItem.getOrderNumber(), orderItem.getQuantity(), orderItem.getTotalPrice(), orderItem.getAppliedPromo());
            i++;
        }
        System.out.println("==================================================");
    }

    public double getTotalPrice(double itemSubTotal) {
        double total = 0.0;
        total = itemSubTotal + user.getShippingCost();
        return total;
    }

    public void getLastOrderDetails() {
        System.out.println();
        System.out.println("Kode Pelanggan: " + user.getUserID());
        System.out.println("Nama: " + user.getUserName());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println("Tanggal Pesanan: " + LocalDate.now().format(dateFormat));
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal");
        System.out.println("==================================================");
        int i=1;
        OrderItem lastOrdered =  orders.get(orders.size() - 1);
        for (CartItem cartItem : lastOrdered.getOrderedItem()) {
            Menu menu = cartItem.getMenu();
            String menuName = menu.getMenuName().length() >= 20 ? menu.getMenuName().substring(0, 20) : menu.getMenuName();
            String subtotal = formatter.format(menu.getPrice()*cartItem.getQuantity());
            System.out.printf("%3d | %-20s | %3d | %8s \n", i, menuName, cartItem.getQuantity(), subtotal);
            i++;
        }
        System.out.println("==================================================");
        String subtotal = formatter.format(lastOrdered.getItemSubTotal()); //subtotal
        String delivery = formatter.format(user.getShippingCost()); //biaya ongkir
        String total = formatter.format(getTotalPrice(lastOrdered.getItemSubTotal())); //total harga
        String balance = formatter.format(user.getBalance()); //saldo
        System.out.printf("%-27s: %9s\n", "Total",subtotal);
        if (user instanceof Member) {
            if (((Member) user).getUserPromo() != null) {
                if (((Member) user).getUserPromo() instanceof DiscountPromo) {
                    String discount = formatter.format(-((Member) user).getUserPromo().getPricePromo(lastOrdered.getItemSubTotal()));
                    System.out.printf("%-27s: %9s\n", "PROMO: " + ((Member) user).getUserPromo().getPromoCode(), discount);
                }
            }
        }
        System.out.printf("%-27s: %9s\n", "Ongkos kirim", delivery);
        if (user instanceof Member) {
            if (((Member) user).getUserPromo() != null){
                if (((Member) user).getUserPromo() instanceof DeliveryPromo){
                    String deliveryOff = formatter.format(-((Member) user).getUserPromo().getPricePromo(lastOrdered.getItemSubTotal()));
                    System.out.printf("%-27s: %9s\n", "PROMO: " + ((Member) user).getUserPromo().getPromoCode(), deliveryOff);
                }
            }
        }
        System.out.println("==================================================");
        System.out.printf("%-27s: %9s\n", "Total", total);
        if (user instanceof Member) {
            if (((Member) user).getUserPromo() != null){
                if (((Member) user).getUserPromo() instanceof CashbackPromo){
                    String cashback = formatter.format(((Member) user).getUserPromo().getPricePromo(lastOrdered.getItemSubTotal()));
                    System.out.printf("%-27s: %9s\n", "PROMO: " + ((Member) user).getUserPromo().getPromoCode(), cashback);
                }
            }
        }
        System.out.printf("%-27s: %9s\n", "Sisa Saldo", balance); //shopping cart Saldo bukan Sisa saldo
        System.out.println();
    }
}
