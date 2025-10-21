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

  // pure
  @VisibleForTesting // aceasta met package-protected e apelabila doar din src/test
  static ApplyCouponsResult applyCoupons(
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
    return new ApplyCouponsResult(usedCoupons, finalPrices);
  }

  // TODO extract complexity into a pure function
  public Map<Long, Double> computePrices(
      long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds); // WHERE ID IN (?,?,...?)
    Map<Long, Double> initialPrices = findInitialPrices(internalPrices, products);
    ApplyCouponsResult result = PureFunction.applyCoupons(products, initialPrices, customer.coupons());
    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> findInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId());
      }
      initialPrices.put(product.getId(), price);
    }
    return initialPrices;
  }

  //  public void method() { // ⚠️(1)tot poate fi chemata de alt proiect
  private void method(int p/*1*/) { // ⚠️2:prin reflection
    int x = 1;//✅ dileste-o
  }

  record ApplyCouponsResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {}
}

