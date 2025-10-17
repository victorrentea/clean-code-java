package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

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

  //  @RolesAllowed/@Secured(ROLE_ADMIN)
//  @Timed
//  @Transactional
//  @TransactionAttribute
  // TODO extract most complexity into a pure function: clear inputs/outputs, temporal couplings, easy to test (a pure function requires not MOCKS to test). just data
  public Map<Long, Double> computePrices(long customerId,
                                         List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId); // SELECT
    List<Product> products = productRepo.findAllById(productIds); // SELECT where ID in (?,?..,)

    Map<Long, Double> initialPrices = fetchInitialPrices(internalPrices, products);
    ApplyCouponsResult result = applyCoupons(products, initialPrices, customer.coupons());

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  @VisibleForTesting
  record ApplyCouponsResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {}

  //pure, easy to test
  @VisibleForTesting
  // allows this method to be package-private for testing purposes
  // no code in src/main/java/ can use this without Sonar alarms
  ApplyCouponsResult applyCoupons(
      List<Product> products,
      Map<Long, Double> initialPrices,
      List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>(); // accumulators
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

  private Map<Long, Double> fetchInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
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
}

