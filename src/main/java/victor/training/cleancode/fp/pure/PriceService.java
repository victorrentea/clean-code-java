package victor.training.cleancode.fp.pure;

import lombok.RequiredArgsConstructor;
import lombok.Value;
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
  public Map<Long, Double> computePrices(
          long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);

    PriceComputationResult result = computePrice(internalPrices, customer, products);

    couponRepo.markUsedCoupons(customerId, result.getUsedCoupons());
    return result.getFinalPrices();
  }

  @NotNull
  private PriceComputationResult computePrice(Map<Long, Double> internalPrices, Customer customer, List<Product> products) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPrices.fetchPrice(product.getId());
      }
      for (Coupon coupon : customer.getCoupons()) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    PriceComputationResult result = new PriceComputationResult(usedCoupons, finalPrices);
    return result;
  }
}

@Value
class PriceComputationResult {
  List<Coupon> usedCoupons;
  Map<Long, Double> finalPrices;
}