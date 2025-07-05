package victor.training.cleancode.support;

import java.util.List;

public interface CouponRepo {
  void markUsedCoupons(long customerId, List<Coupon> usedCoupons);
}
