package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDateTime;
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
  public Map<Long, Double> computePrices(long customerId,
                                         List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);

    Map<Long, Double> initialPrices = resolveInitialPrices(internalPrices, products);

    DiscountResult result = applyDiscountsPure(products, initialPrices, customer.coupons());

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.discountedPrices();
  }

  private Map<Long, Double> resolveInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId());
      }
      // internalPrices.put(product.getId(), price); // BAD: changes the state of the map in the caller
      // product.setPrice( price); // BAD: add 1 field to product
      // thirdPartyPricesMap.put(product.getId(), price);
      // discountedPrices.put(product.getId(), price); // neah. it's not YET discounted
      initialPrices.put(product.getId(), price);
    }
    return initialPrices;
  }

  // OMG a pure function. this is what I want to Unit Test. Not the dependenchy hell above (requires 4 @Mocks)
  @VisibleForTesting // only src/tests can call this method, or this class. Not the rest of the production code
  // Subcutaneous Testing: test under the public api, the pure function within
  static DiscountResult applyDiscountsPure(List<Product> products,
                                           Map<Long, Double> initialPrices,
                                           List<Coupon> customerCoupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> discountedPrices = new HashMap<>();
    for (Product product : products) {
      Double price = initialPrices.get(product.getId());
      for (Coupon coupon : customerCoupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      discountedPrices.put(product.getId(), price);
    }
    return new DiscountResult(usedCoupons, discountedPrices);
  }
  record DiscountResult(List<Coupon> usedCoupons, Map<Long, Double> discountedPrices) {

  }
}

