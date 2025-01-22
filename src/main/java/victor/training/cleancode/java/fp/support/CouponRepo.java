package victor.training.cleancode.java.fp.support;

import java.util.List;

public interface CouponRepo {
  void markUsedCoupons(long customerId, List<Coupon> usedCoupons);
}
