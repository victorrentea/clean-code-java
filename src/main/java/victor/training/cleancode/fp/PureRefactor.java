package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import victor.training.cleancode.fp.support.*;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class PureRefactor {
  private final CustomerRepo customerRepo;
  private final ThirdPartyPrices thirdPartyPricesApi;
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

  @VisibleForTesting
  static PriceCalculationResult doComputePrices(Customer customer, List<Product> products, Map<Long, Double> resolvedPrices) {
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
    List<Long> unknownProductIds = products.stream()
            .map(Product::getId)
            .filter(id -> !internalPrices.containsKey(id))
            //.filter(not(internalPrices::containsKey))
            .collect(Collectors.toList());
    Map<Long, Double> thirdPartyPrices = thirdPartyPricesApi.fetchAllPrices(unknownProductIds);

    Map<Long, Double> resolvedPrices = new HashMap<>();
    resolvedPrices.putAll(internalPrices);
    resolvedPrices.putAll(thirdPartyPrices);
    return resolvedPrices;
  }

  private void fetchAllPrices(List<Long> unknownProductIds) {
    throw new RuntimeException("Method not implemented");
  }

}

@Value
class PriceCalculationResult {
  Map<Long, Double> finalPrices;
  List<Coupon> usedCoupons;
}
