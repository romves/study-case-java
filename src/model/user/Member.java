package model.user;

import model.promo.Promo;
import model.promo.PromoCode;
import utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class Member extends User implements Promo {
    private String memberName;
    private Date joinedDate;
    private PromoCode promoCode;

    public Member(String id, String memberName, String joinedDate, double balance) throws ParseException {
        super(id, balance);
        this.memberName = memberName;
        this.joinedDate = DateUtils.convertStringToDate(joinedDate);
        this.promoCode = null;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberAge() {
        return 0;
    }

    @Override
    public void applyPromoCode(PromoCode promoCode) {
        //TODO add promo checking
        this.promoCode = promoCode;
        System.out.println("Promo code applied");
    }

    @Override
    public double getTotal() {
        if (promoCode != null ) {
            double total;
            total = getCart().calculateTotal() - (getCart().calculateTotal() * promoCode.getPercentCut()/100);
            return total;
        }
        return getCart().calculateTotal();
    }
}


