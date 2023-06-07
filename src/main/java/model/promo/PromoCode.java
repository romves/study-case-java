package model.promo;

import utils.LocalDateUtils;

import java.time.LocalDate;

import static utils.LocalDateUtils.isCurrentDateInRange;

public class PromoCode {
     String promoCode;
     LocalDate startedAt;
     LocalDate expiredAt;
     int percentCut;
     double maxPriceCut;
     double minApplicablePrice;

     public PromoCode(String promoCode, String startedAt, String expiredAt, String percentCut,  double minApplicablePrice, double maxPriceCut) {
          this.promoCode = promoCode;
          this.startedAt = LocalDateUtils.convertStringToLocalDate(startedAt);
          this.expiredAt = LocalDateUtils.convertStringToLocalDate(expiredAt);
          this.percentCut = Integer.parseInt(percentCut.replace("%", ""));
          this.maxPriceCut = maxPriceCut;
          this.minApplicablePrice = minApplicablePrice;
     }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    public void setPercentCut(int percentCut) {
        this.percentCut = percentCut;
    }

    public void setMaxPriceCut(double maxPriceCut) {
        this.maxPriceCut = maxPriceCut;
    }

    public void setMinApplicablePrice(double minApplicablePrice) {
        this.minApplicablePrice = minApplicablePrice;
    }
     
     
     
     public String getPromoCode() {
          return promoCode;
     }

     public LocalDate getStartedAt() {
          return startedAt;
     }

     public LocalDate getExpiredAt() {
          return expiredAt;
     }

     public int getPercentCut() {
          return percentCut;
     }

     public double getMaxPriceCut() {
          return maxPriceCut;
     }

     public double getMinApplicablePrice() {
          return minApplicablePrice;
     }

     public boolean isValid() {
          return isCurrentDateInRange(startedAt, expiredAt);
     }

     public double getPricePromo(double normalPrice) {
         double priceOff = 0.0;
         priceOff = normalPrice * getPercentCut()/100;
         return priceOff > maxPriceCut ? maxPriceCut : priceOff;
     }
}

