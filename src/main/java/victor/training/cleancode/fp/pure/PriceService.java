package victor.training.cleancode.fp.pure;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class PriceService {

  private final CustomerRepo customerRepo;
  private final ThirdPartyPrices thirdPartyPrices;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;

  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId,
                                         List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);
    Map<Long, Double> resolvedPrices = resolveExternalPrices(internalPrices, products);

    PriceCalculationResult result = computePrices(customer, products, resolvedPrices);

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> resolveExternalPrices(Map<Long, Double> internalPrices, List<Product> products) {
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

  // Iubim fct pure pentru ca:
  // 1) sunt usor de testat fara mockuri
  // 2) usor de inteles
  // 3) le poti chema de cate ori vrei (ca sunt rapide)
  // 4) nu lasa loc de cuplare temporala (nu exista dependente de date invizibile)
  @VisibleForTesting
  static PriceCalculationResult computePrices(Customer customer, List<Product> products, Map<Long, Double> resolvedPrices) {
    Map<Long, Double> finalPrices = new HashMap<>();
    List<Coupon> usedCoupons = new ArrayList<>();
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
}

record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {}