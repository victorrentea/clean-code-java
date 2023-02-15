package victor.training.cleancode.fp;

import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import victor.training.cleancode.fp.support.*;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class PureRefactor {
  private final CustomerRepo customerRepo;
  private final ThirdPartyPrices thirdPartyPrices;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;


  // PURE FUNCTION =
  // 1 produces the same results for the same params
  // 2 no side effects


  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);

    Map<Long, Double> resolvedPrices = resolvePricesFromThirdParty(internalPrices, products);

    PriceCalculationResult result = doComputePrices(customer, products, resolvedPrices);

    // rabbit.send("mesasge"); api post
    couponRepo.markUsedCoupons(customerId, result.getUsedCoupons());
    return result.getFinalPrices();
  }

  @NotNull
  private static PriceCalculationResult doComputePrices(Customer customer, List<Product> products, Map<Long, Double> resolvedPrices) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = resolvedPrices.get(product.getId());
      for (Coupon coupon : customer.getCoupons()) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new PriceCalculationResult(finalPrices, usedCoupons);
  }

  private Map<Long, Double> resolvePricesFromThirdParty(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> resolvedPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPrices.fetchPrice(product.getId());
      }
      resolvedPrices.put(product.getId(), price);
    }
    return resolvedPrices;
  }

}

@Value
class PriceCalculationResult {
  Map<Long, Double> finalPrices;
  List<Coupon> usedCoupons;
}
