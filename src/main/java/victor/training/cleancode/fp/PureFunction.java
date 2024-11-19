package victor.training.cleancode.fp;

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

  private static DiscountingResult applyCoupons(
      List<Product> products,
      Map<Long, Double> resolvedPrices,
      Customer customer) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = resolvedPrices.get(product.getId());
      for (Coupon coupon : customer.coupons()) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new DiscountingResult(usedCoupons, finalPrices);
//    return new Tuple2<>(usedCoupons, finalPrices) // #RAU
  }

  // 4 @Test x 4 @Mock = 😐
  // 9 @Test x 4 @Mock = 😭🤯
  // TODO extract complexity into a pure function
  public Map<Long, Double> computePrices(
      long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId); // SELECT WHERE ID = ?
    List<Product> products = productRepo.findAllById(productIds); // SELECT WHERE ID IN (?, ?)

    Map<Long, Double> resolvedPrices = resolvePrices(internalPrices, products);

    DiscountingResult result = applyCoupons(products, resolvedPrices, customer);

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> resolvePrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> resolvedPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId());
      }
      resolvedPrices.put(product.getId(), price);
    }
    return resolvedPrices;
  }

  private record DiscountingResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }
}

