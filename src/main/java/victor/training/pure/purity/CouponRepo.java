package victor.training.pure.purity;

import java.util.List;

public interface CouponRepo {
   void markUsedCoupons(long customerId, List<Coupon> usedCoupons);
}
