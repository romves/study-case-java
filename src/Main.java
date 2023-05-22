import model.*;
import model.user.Guest;
import model.user.Member;
import model.user.User;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //create menu
        Menu menu = new Cetak("M001","Sistem Operasi", 45_000);
        Menu menu1 = new Cetak("M002","PBO", 55_000);

        //create user
        User user = new Guest("A001", 110_000);
        User user1 = new Member("A002", "Budi","2023/05/20", 20_000);

        //create promo

        //add to cart
        user.addToCart(menu);
        user.addToCart(menu);
        user.addToCart(menu1);

        //view cart
        user.viewCartItems();

        //popup balance
        user.balanceTopup(100_000);
        user1.balanceTopup(200_000);

        //checkout
        user.checkout();

        //generate receipt

    }
}