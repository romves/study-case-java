package model.promo;

public class CashbackPromo extends PromoCode {
    public CashbackPromo(String promoCode, String startedAt, String expiredAt, String percentCut, double minApplicablePrice, double maxPriceCut) {
        super(promoCode, startedAt, expiredAt, percentCut, minApplicablePrice, maxPriceCut);
    }
}
