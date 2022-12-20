package victor.training.cleancode.fp.pure;

import java.util.List;

interface CouponRepo {
  void markUsedCoupons(long customerId, List<Coupon> usedCoupons);
}
