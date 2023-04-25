package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import victor.training.cleancode.fp.support.*;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.*;

@RequiredArgsConstructor
class PureRefactor {
  private final CustomerRepo customerRepo;
  private final ThirdPartyPrices thirdPartyPrices;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;

  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);
    Map<Long, Double> allPrices = resolveAllPrices(internalPrices, products);

    PriceCalculationResult result = doComputePrices(customer, products, allPrices);

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> resolveAllPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> allPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPrices.fetchPrice(product.getId());
      }
      allPrices.put(product.getId(), price);
    }
    return allPrices;
  }

  @NotNull
  @VisibleForTesting // daca sonaru te prinde ca o chemi din afara clasei asteia in prod
  // TZIPA SONARU
   static PriceCalculationResult doComputePrices(Customer customer,
                                                        List<Product> products,
                                                        Map<Long, Double> allPrices) {
    Map<Long, Double> finalPrices = new HashMap<>();
    List<Coupon> usedCoupons = new ArrayList<>();
    for (Product product : products) {
      Double price  = allPrices.get(product.getId());
      for (Coupon coupon : customer.getCoupons()) {
        if (coupon.autoApply()
            && coupon.isApplicableFor(product)
            && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new PriceCalculationResult(usedCoupons, finalPrices);
  }

  private record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }
}

