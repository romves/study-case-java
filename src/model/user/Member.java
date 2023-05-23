package model.user;

import model.promo.Promo;
import model.promo.PromoCode;
import utils.LocalDateUtils;

import java.time.LocalDate;
import java.time.Period;

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
        Period period = Period.between(joinedDate, currentDate);
        int age = period.getDays();
        return 0;
    }

    @Override
    public void applyPromoCode(PromoCode promoCode) {
        //TODO add promo checking
        if (getCart().getTotalPrice() > promoCode.getMinApplicablePrice() && getMemberAge() > 30 ) {
            if (promoCode.isValid()) {
                this.promoCode = promoCode;
                System.out.println("Promo code applied");
            }
            System.out.println("Promo code expired");
        }
        System.out.println("Promo Ineligible");
    }

    public PromoCode getPromoCode() {
        return promoCode;
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


