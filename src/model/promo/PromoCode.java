package model.promo;

import utils.LocalDateUtils;

import java.time.LocalDate;

import static utils.LocalDateUtils.isCurrentDateInRange;

public class PromoCode {
     PROMO type;
     String promoCode;
     LocalDate startedAt;
     LocalDate expiredAt;
     int percentCut;
     double maxPriceCut;
     double minApplicablePrice;

     public PromoCode(PROMO type, String promoCode, String startedAt, String expiredAt, String percentCut, double maxPriceCut, double minAppliablePrice) {
          this.type = type;
          this.promoCode = promoCode;
          this.startedAt = LocalDateUtils.convertStringToLocalDate(startedAt);
          this.expiredAt = LocalDateUtils.convertStringToLocalDate(expiredAt);
          this.percentCut = Integer.parseInt(percentCut.replace("%", ""));
          this.maxPriceCut = maxPriceCut;
          this.minApplicablePrice = minAppliablePrice;
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

     //TODO DURUNG BENER
}

