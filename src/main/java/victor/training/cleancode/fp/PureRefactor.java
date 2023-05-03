package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
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

  // pe orice usecase complicat, fuctia top level trebuie sa curga ca o poveste,
  // sa explice pasii principali
  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds,
                                         Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = findProducts(productIds);
    Map<Long, Double> initialPrices = resolveInitialPrices(internalPrices, products);
    PriceCalculationResult result = doCalculatePrices(products, initialPrices, customer.getCoupons());
    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.results();
  }

  private List<Product> findProducts(List<Long> productIds) {
    List<Product> products = productRepo.findAllById(productIds); // WHERE P.ID IN (?,?...)
    if (products.size() != productIds.size()) {
      throw new IllegalArgumentException("n-am gasit toate produsele");
    }
    return products;
  }

  private record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> results) {
  }
  // @Value
  //  private record PriceCalculationResult {
  //  List<Coupon> usedCoupons;
  //  Map<Long, Double> results;
  //  }

  // 6 teste cu 0 mockuri pt partea cea mai complexa a logicii
  // PURE fucntion: statica (nu face retea) si nu modifica param
  @VisibleForTesting // tipa sonaru daca din prod din alta clasa o chemi
  // doar testele ar trebui sa o cheme
  // = teste subcutanate. o testezi separat FARA MOCKURI
  private static PriceCalculationResult doCalculatePrices(List<Product> products,
                                                          Map<Long, Double> initialPrices,
                                                          List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> results = new HashMap<>();
    for (Product product : products) {
      Double price = initialPrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      results.put(product.getId(), price);
    }
    return new PriceCalculationResult(usedCoupons, results);
  }

  private Map<Long, Double> resolveInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPrices.fetchPrice(product.getId()); // modu bun: cu Adapter pattern
      }
      initialPrices.put(product.getId(), price);
      //      product.setInitialPriceDoarPanaAplicCupoanele(price); NU!
    }
    return initialPrices;
  }
}

