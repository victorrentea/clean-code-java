package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import victor.training.cleancode.fp.support.*;

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

  // Functia pura =
  // - nu face retea (GET SELECT INSERT POST kafka)
  // - nu modifica stare (campurile obiectelor din jur)

  // daca o fct statica (nu folos dep) ia doar param imutabili => e pura.
  @VisibleForTesting // din orice cod de prod, NU o poti chema. E package protected doar pt testare
  static PriceCalculationResult doComputePrices(Customer customer,
                                                List<Product> products,
                                                Map<Long, Double> resolvedPrices) {
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
    return new PriceCalculationResult(usedCoupons, finalPrices);
  }

  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);
    Map<Long, Double> resolvedPrices = getResolvedPrices(internalPrices, products);
    PriceCalculationResult result = doComputePrices(customer, products, resolvedPrices);
    couponRepo.markUsedCoupons(customerId, result.getUsedCoupons());
    return result.getFinalPrices();
  }

  @NotNull
  private Map<Long, Double> getResolvedPrices(Map<Long, Double> internalPrices, List<Product> products) {
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
  List<Coupon> usedCoupons;
  Map<Long, Double> finalPrices;
}

