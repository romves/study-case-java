package model.promo;

public class DiscountPromo extends PromoCode{
    public DiscountPromo(String promoCode, String startedAt, String expiredAt, String percentCut, double minApplicablePrice, double maxPriceCut) {
        super(promoCode, startedAt, expiredAt, percentCut, minApplicablePrice, maxPriceCut);
    }
}
