package model.user;

import model.promo.Promo;
import model.promo.PromoCode;
import utils.LocalDateUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member extends User implements Promo{
    private final LocalDate joinedDate;
    private PromoCode promoCode;

    public Member(String id, String userName, String joinedDate, double balance) {
        super(id, balance);
        this.userName = userName;
        this.joinedDate = LocalDateUtils.convertStringToLocalDate(joinedDate);
        this.promoCode = null;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMemberAge() {
        LocalDate currentDate = LocalDate.now();
        return (int)ChronoUnit.DAYS.between(joinedDate, currentDate);
    }

    @Override
    public void applyPromoCode(PromoCode promoCode) {
        //TODO add promo checking
        if (getCart().getItemSubTotal() > promoCode.getMinApplicablePrice() && getMemberAge() > 30 ) {
            if (promoCode.isValid()) {
                this.promoCode = promoCode;
                System.out.println("APPLY_PROMO SUCCESS: " + getUserPromo().getPromoCode());
                return;
            }
            System.out.println("Promo code expired");
            return;
        }
        System.out.println("Promo Ineligible");
    }

    public PromoCode getUserPromo() {
        return promoCode;
    }

    public void clearPromoCode() {
        this.promoCode = null;
    }

    //    @Override
//    public double getSubtotal() {
//        if (promoCode != null ) {
//            double total;
//            total = getCart().calculateTotal() - (getCart().calculateTotal() * promoCode.getPercentCut()/100);
//            return total;
//        }
//        return getCart().calculateTotal();
//    }
}


