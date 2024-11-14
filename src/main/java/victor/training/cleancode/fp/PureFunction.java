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

  private static PriceCalculationResult applyCouponsToPrices(
      List<Product> products,
      Map<Long, Double> initialPrices,
      List<Coupon> coupons) {

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
    return new PriceCalculationResult(usedCoupons, finalPrices);
  }

  // TODO extract complexity into a pure function
  public Map<Long, Double> computePrices(
      long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
//    assert internalPrices != null; // neah... assert can be disabled in prod with a JVM flag
//    if (internalPrices== null) throw !!! = defensive programming== worse
//    if (productIds.isEmpty()) throw !!! = defensive programming
    // a key technique used by library code (used by unknown developers)
    // but to avoid in core business logic
    // you shall never ever ever see something of type collection being equal to null!!
    // instead that thing (field, variable,param) should come in empty collection!!!!!
    Customer customer = customerRepo.findById(customerId); // SELECT * FROM Customer WHERE id = ?
    List<Product> products = productRepo.findAllById(productIds);// SELECT * FROM Product WHERE id IN (?, ?, ?)

    Map<Long, Double> initialPrices = resolveInitialPrices(products, internalPrices);

    PriceCalculationResult result = applyCouponsToPrices(products, initialPrices, customer.coupons());

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> resolveInitialPrices(List<Product> products, Map<Long, Double> internalPrices) {
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

  private record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }
}

