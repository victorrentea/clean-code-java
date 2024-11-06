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
  private final ThirdPartyPricesApi thirdPartyPricesApi;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;

  // TODO extract complexity into a pure function

  public Map<Long, Double> computePrices(
      long customerId,
      List<Long> productIds,
      Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId); // DB
    List<Product> products = productRepo.findAllById(productIds); // DB WHERE ID IN (?,...)

    Map<Long, Double> initialPrices = resolveInitialPrices(internalPrices, products);

    CouponApplicationResult result = applyCoupons(products, initialPrices, customer.coupons());

    couponRepo.markUsedCoupons(customerId, result.usedCoupons()); // DB
    return result.finalPrices();
  }
  private Map<Long, Double> resolveInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId()); // API call (REST)
      }
      initialPrices.put(product.getId(), price);
    }
    return initialPrices;
  }

  // perfect target for testing
  @VisibleForTesting // means "package private" + "visible for testing"
  // that is, it's not part of the public API, but it's visible for testing
  // Sonar/IntelliJ will highlight an error if any other class in src/main uses this method
  static CouponApplicationResult applyCoupons(
      List<Product> products,
      Map<Long, Double> initialPrices,
      List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = initialPrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new CouponApplicationResult(usedCoupons, finalPrices);
  }

  // complexity calls for more structure
  @VisibleForTesting
  record CouponApplicationResult(
      List<Coupon> usedCoupons,
      Map<Long, Double> finalPrices) {
  }
}

