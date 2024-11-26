package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class PureFunction {
  private final CustomerRepo customerRepo;
  //  private final ThirdPartyPricesApi thirdPartyPricesApi;
  private final CouponRepo couponRepo;
  private final ThirdPartyPriceProvider thirdPartyPriceProvider;
  private final ProductRepo productRepo;

  // TODO extract complexity into a pure function

  public Map<Long, Double> computePrices( // High Level, Imperative Shell
      long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);
    Map<Long, Double> initialPrices = thirdPartyPriceProvider.resolvePrices(internalPrices, products);
    ApplyCouponsResult result = applyCoupons(products, initialPrices, customer.coupons()); // complex logic extyracted as a PURE FUNCTION
    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }
  private record ApplyCouponsResult(
      List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }
//  public static int hellInBackend;


  @VisibleForTesting // + package-protected = subcutaneous tests
  //  will fail Sonar/IntelliJ all other static code analysis when another class in the same packe
  // in src/main (not tests) accesses this method
  // if anyone says "blapshemy", move as public to CouponApplier as static method (no @Component)
  static ApplyCouponsResult applyCoupons(
      List<Product> products,
      Map<Long, Double> initialPrices,
      List<Coupon> coupons) {// testable with 0 mocks !💖
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      double price = initialPrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new ApplyCouponsResult(usedCoupons, finalPrices);
  }

}

