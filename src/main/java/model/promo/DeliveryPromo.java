package model.promo;

public class DeliveryPromo extends PromoCode{
    public DeliveryPromo(String promoCode, String startedAt, String expiredAt, String percentCut, double minApplicablePrice, double maxPriceCut) {
        super(promoCode, startedAt, expiredAt, percentCut, minApplicablePrice, maxPriceCut);
    }
}
