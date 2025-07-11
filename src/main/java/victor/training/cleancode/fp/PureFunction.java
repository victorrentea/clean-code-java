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

  public Map<Long, Double> computePrices(
      long customerId,
      List<Long> productIds,
      Map<Long, Double> internalPrices) {

    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds); // WHERE ID IN (?,?..?)§
    var resolvedPrices = resolvePrices(internalPrices, products);
    var result = applyCoupons(products, resolvedPrices, customer);
    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> resolvePrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> resolvedPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId()); // 10-1000ms
      }
      resolvedPrices.put(product.getId(), price);
    }
    return resolvedPrices;
  }

  /* package */ record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {}

  // static = proof ca nu depinde de dependente injectate de Spring
  @VisibleForTesting
  static PriceCalculationResult applyCoupons(
      List<Product> products,
      Map<Long, Double> resolvedPrices,
      Customer customer) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) { // nu vei pierde performanta: aducerea produselor din Db oricum e ff lunga
      Double price = resolvedPrices.get(product.getId());
      for (Coupon coupon : customer.coupons()) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new PriceCalculationResult(usedCoupons, finalPrices);
  }

  private void f1() {
  }

  private void f2() {
  }

  private void f3() {
  }

}
