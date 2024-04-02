package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
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

  // TODO extract complexity into a pure function
  public Map<Long, Double> computePrices(long customerId,
                                         List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId).orElseThrow();
    List<Product> products = productRepo.findAllById(productIds);


    Map<Long, Double> extractedPrices = extractPrices(internalPrices, products);

    // apply coupons
    DiscountedPrice result = applyCoupons(products, extractedPrices, customer.coupons());

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private record DiscountedPrice(List<Coupon> usedCoupons,
                                 Map<Long, Double> finalPrices) {
  }
  // pure function
  @VisibleForTesting // only tests should call this. Sonar+IntelliJ warns if another prod class uses this
  static DiscountedPrice applyCoupons(List<Product> products,
                                       Map<Long, Double> extractedPrices,
                                       List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = extractedPrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() &&
            coupon.isApplicableFor(product) &&
            !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new DiscountedPrice(usedCoupons, finalPrices);
  }


  private Map<Long, Double> extractPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> extractedPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId());
      }
      extractedPrices.put(product.getId(), price);
    }
    return extractedPrices;
  }
}

