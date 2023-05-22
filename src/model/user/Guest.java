package model.user;

import model.Menu;
import model.cart.CartItem;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Guest extends User{
    private String userName;
    public Guest (String id, double balance){
        super(id, balance);
        this.userName = "Guest";
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void generateReceipt() {
        System.out.println("");
        System.out.println("Kode Pelanggan: " + this.getUserID());
        System.out.println("Nama: " + this.getUserName());

//        System.out.println("Nomor Pesanan: " + this.orderNo);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

        System.out.println("Tanggal Pesanan: " + LocalDate.now().format(dateFormat));

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal");
        System.out.println("==================================================");
        int i=1;
        List<CartItem> cartItems = getCart().getItems();
        for (CartItem cartItem : cartItems) {
            Menu menu = cartItem.getMenu();
            String menuName = menu.getMenuName().length() >= 20 ? menu.getMenuName().substring(0, 20) : menu.getMenuName();
            String subtotal = formatter.format(menu.getPrice()*cartItem.getQuantity());
            System.out.printf("%3d | %-20s | %3d | %8s \n", i, menuName, cartItem.getQuantity(), subtotal);
            i++;
        }
        System.out.println("==================================================");
        String subtotal = formatter.format(this.getSubtotal()); //subtotal
        String delivery = formatter.format(15000); //biaya ongkir
//        String total = formatter.format(this.getTotalPrice()); //total harga
        String balance = formatter.format(getBalance()); //saldo
        System.out.printf("%-27s: %9s\n", "Total",subtotal);
//
        System.out.printf("%-27s: %9s\n", "Ongkos kirim", delivery);
//
        System.out.println("==================================================");
//        System.out.printf("%-27s: %9s\n", "Total", total);
//
        System.out.printf("%-27s: %9s\n", "Sisa saldo", balance); //shopping cart Saldo bukan Sisa saldo
        System.out.println("");

    }
}
