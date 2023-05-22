package model.user;

import model.Menu;
import model.cart.CartItem;
import model.promo.Promo;
import model.promo.PromoCode;
import utils.LocalDateUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Member extends User implements Promo {
    private String userName;
    private LocalDate joinedDate;
    private PromoCode promoCode;

    public Member(String id, String userName, String joinedDate, double balance) {
        super(id, balance);
        this.userName = userName;
        this.joinedDate = LocalDateUtils.convertStringToLocalDate(joinedDate);
        this.promoCode = null;
    }

    public String getMemberName() {
        return userName;
    }

    public void setMemberName(String memberName) {
        this.userName = memberName;
    }

    public int getMemberAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(joinedDate, currentDate);
        int age = period.getDays();
        return 0;
    }

    @Override
    public void applyPromoCode(PromoCode promoCode) {
        //TODO add promo checking
        if (getCart().calculateTotal() > promoCode.getMinApplicablePrice() && getMemberAge() > 30 ) {
            if (promoCode.isValid()) {
                this.promoCode = promoCode;
                System.out.println("Promo code applied");
            }
            System.out.println("Promo code expired");
        }
        System.out.println("Uneligible");
    }

    @Override
    public double getSubtotal() {
        if (promoCode != null ) {
            double total;
            total = getCart().calculateTotal() - (getCart().calculateTotal() * promoCode.getPercentCut()/100);
            return total;
        }
        return getCart().calculateTotal();
    }

    @Override
    public void generateReceipt() {
        System.out.println("");
        System.out.println("Kode Pelanggan: " + this.getUserID());
        System.out.println("Nama: " + this.getMemberName());
//TODO ORDER NUMBER
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
//        if (this.getPromo() != null){
//            if (this.getPromo() instanceof DiscountPromotion){
//                String discount = formatter.format(-
//
//                        this.getPromo().getTotalPriceOff(this));
//                System.out.printf("%-27s: %9s\n", "PROMO: " +
//
//                        this.getPromo().getPromoCode(), discount);
//            }
//        }
        System.out.printf("%-27s: %9s\n", "Ongkos kirim", delivery);
//        if (this.getPromo() != null){
//            if (this.getPromo() instanceof DeliveryFeePromotion){
//                String deliveryOff = formatter.format(-
//                        this.getPromo().getTotalShippingFeeOff(this));
//
//                System.out.printf("%-27s: %9s\n", "PROMO: " +
//
//                        this.getPromo().getPromoCode(), deliveryOff);
//            }
//        }
        System.out.println("==================================================");
//        System.out.printf("%-27s: %9s\n", "Total", total);
//        if (this.getPromo() != null){
//            if (this.getPromo() instanceof CashbackPromotion){
//                String cashback = formatter.format(-
//
//                        this.getPromo().getTotalCashback(this));
//
//                System.out.printf("%-27s: %9s\n", "PROMO: " +
//
//                        this.getPromo().getPromoCode(), cashback);
//            }
//        }
        System.out.printf("%-27s: %9s\n", "Sisa saldo", balance); //shopping cart Saldo bukan Sisa saldo
        System.out.println("");
    }
}


