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

  // TODO extract most complexity into a pure function
  public Map<Long, Double> computePrices(
      final long customerId,
      final List<Long> productIds,
      final Map<Long, Double> internalPrices) {
    final Customer customer = customerRepo.findById(customerId); //+
    final List<Product> products = productRepo.findAllById(productIds); // WHERE id IN (?,?..)

    final Map<Long, Double> initialPrices = fetchInitialPrices(internalPrices, products);

    // apply coupons
    final List<Coupon> usedCoupons = new ArrayList<>();
    final Map<Long, Double> finalPrices = new HashMap<>();
    for (final Product product : products) {
      Double price = initialPrices.get(product.getId());
      for (final Coupon coupon : customer.coupons()) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    couponRepo.markUsedCoupons(customerId, usedCoupons);
    return finalPrices;
  }

  private Map<Long, Double> fetchInitialPrices(final Map<Long, Double> internalPrices, final List<Product> products) {
    final Map<Long, Double> initialPrices = new HashMap<>();
    for (final Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId());//+
      }
      initialPrices.put(product.getId(), price);
    }
    return initialPrices;
  }
}

