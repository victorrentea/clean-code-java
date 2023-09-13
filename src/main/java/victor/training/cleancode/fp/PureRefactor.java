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
class PureRefactor {
  private final CustomerRepo customerRepo;
  private final ThirdPartyPrices thirdPartyPricesApi;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;

  // TODO extract a pure function with as much logic possible
  // "Facade" high level view al UC
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);
    Map<Long, Double> basePriceForProduct = resolvePrices(internalPrices, products);
    PriceCalculationResult result = applyCoupons(products, basePriceForProduct, customer.getCoupons());
    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  // nu e pure
  private Map<Long, Double> resolvePrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> resolvedPrices = new HashMap<>();
    for (Product product : products) {
      Double basePrice = internalPrices.get(product.getId());
      if (basePrice == null) {
        basePrice = thirdPartyPricesApi.fetchPrice(product.getId());
      }
      resolvedPrices.put(product.getId(), basePrice);
    }
    return resolvedPrices;
  }

  private record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }

  // functie statica(no network) care primeste doar ob imutabile(no changes) si nuface time/random este PURE

  // "Teste subcutanate";
  @VisibleForTesting // Sonar va tipa daca vede met asta chemata din /src/main din alta clasa din acelasi pachet
  // au voie doar testele sa o cheme
  // alternativa: muti metoda asta in alta clasa "PriceCalculationService"
  static PriceCalculationResult applyCoupons(List<Product> products, Map<Long, Double> basePrices, List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double basePrice = basePrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          basePrice = coupon.apply(product, basePrice);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), basePrice);
    }
    return new PriceCalculationResult(usedCoupons, finalPrices);
  }

//  class Rezultatul {
//    List<Coupon> usedCoupons = new ArrayList<>();
//    Map<Long, Double> finalPrices = new HashMap<>();
//  }
}

