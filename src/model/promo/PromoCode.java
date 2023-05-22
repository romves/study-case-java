package model.promo;

import utils.DateUtils;
import java.text.ParseException;
import java.util.Date;

public class PromoCode {
     PROMO type;
     String promoCode;
     Date startedAt;
     Date expiredAt;
     int percentCut;
     double maxPriceCut;
     double minApplicablePrice;

     public PromoCode(PROMO type, String promoCode, String startedAt, String expiredAt, String percentCut, double maxPriceCut, double minAppliablePrice) throws ParseException {
          this.type = type;
          this.promoCode = promoCode;
          this.startedAt = DateUtils.convertStringToDate(startedAt);
          this.expiredAt = DateUtils.convertStringToDate(expiredAt);
          this.percentCut = Integer.parseInt(percentCut.replace("%", ""));
          this.maxPriceCut = maxPriceCut;
          this.minApplicablePrice = minAppliablePrice;
     }

     public String getPromoCode() {
          return promoCode;
     }

     public Date getStartedAt() {
          return startedAt;
     }

     public Date getExpiredAt() {
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
}

