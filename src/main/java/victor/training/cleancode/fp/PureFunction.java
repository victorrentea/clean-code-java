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

  private static CouponApplicationResult applyCoupons(
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

  // complexity calls for more structure
  private record CouponApplicationResult(
      List<Coupon> usedCoupons,
      Map<Long, Double> finalPrices) {
  }
}

