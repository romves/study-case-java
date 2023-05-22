import model.*;
import model.promo.PROMO;
import model.promo.PromoCode;
import model.user.Guest;
import model.user.Member;
import model.user.User;

public class Main {
    public static void main(String[] args) {
        //create menu DONE
        Menu menu = new Cetak("M001","Sistem Operasi", 45_000);
        Menu menu1 = new Cetak("M002","PBO", 55_000);
        Menu menu2 = new Fotokopi("M003","A4 WARNA", 2_000);

        //create user DONE
        User user = new Guest("A001", 90_000);
        Member user1 = new Member("A002", "Budi","2023/05/20", 20_000);

        //create promo DONE
        PromoCode promo = new PromoCode(PROMO.DELIVERY, "ONGKIR30", "2023/05/01", "2023/05/31", "30%", 10000,40000);

        //add to cart DONE
        user.addToCart(menu);
        user.addToCart(menu);
        user.addToCart(menu1);
        user.addToCart(menu1);
        user.addToCart(menu2);
        user1.addToCart(menu2);
        user1.addToCart(menu);
        user1.addToCart(menu);

        //remove from cart DONE
        user.removeFromCart(menu);
        user.removeFromCart(menu2);

        //apply promo
        user1.applyPromoCode(promo);

        //view cart
//        user.viewCartItems();

        //generateReceipt
        user.generateReceipt();
        user1.generateReceipt();

        //popup balance
        user.balanceTopup(100_000);
        user1.balanceTopup(10_000);

        //checkout
        user.checkout();
        user1.checkout();

        //show checkout history
    }
}