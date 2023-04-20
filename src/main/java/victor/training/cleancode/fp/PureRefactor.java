package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
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

  @VisibleForTesting
  static PriceCalculationResult doComputePrices(Customer customer,
                                                List<Product> products,
                                                Map<Long, Double> prices) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      // todo exercise to the reader: try to stream() it all
      Double price = prices.get(product.getId());
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

  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);
    Map<Long, Double> resolvedPrices = resolvePrices(productIds, internalPrices);

    PriceCalculationResult result = doComputePrices(customer, products, resolvedPrices);

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> resolvePrices(List<Long> productIds, Map<Long, Double> internalPrices) {
    Map<Long, Double> resolvedPrices = new HashMap<>();
    for (Long productId : productIds) {
      Double price = internalPrices.get(productId);
      if (price == null) {
        price = thirdPartyPrices.fetchPrice(productId); // TODO meetings to open a new BULK API
      }
      resolvedPrices.put(productId, price);
    }
    return resolvedPrices;
  }

  private record PriceCalculationResult(Map<Long, Double> finalPrices, List<Coupon> usedCoupons) {
  }
}

