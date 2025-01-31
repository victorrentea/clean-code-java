package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;

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
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    // high-level function
    // initial data fetching (enrichment)
    Customer customer = customerRepo.findById(customerId); // DB call
    List<Product> products = productRepo.findAllById(productIds); // DB call
    ImmutableMap<Long, Double> initialPrices = resolveInitialPrices(internalPrices, products);

    // calculations, ideally in a pure function
    Result result = applyCoupons(products, initialPrices, customer.coupons());

    // side effects: insert, update, send...
    couponRepo.markUsedCoupons(customerId, result.usedCoupons()); // DB call
    return result.finalPrices();
  }

  // pure function holding the logic of applying coupons << logica e aici!
  // daca tot am izolat logica complexa intr-o metoda pura fara dependente
  // n-ar fi convenabil sa testez direct metoda asta? (evitand astfel cele 4 MOCKURILE necesare)
  @VisibleForTesting
  // permite accesul doar din teste la aceasta metoda
  Result applyCoupons(List<Product> products, ImmutableMap<Long, Double> initialPrices, List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = initialPrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
//      initialPrices.put(product.getId(), price); // update the price for the next iteration
    }
    return new Result(finalPrices, usedCoupons);
  }

  private ImmutableMap<Long, Double> resolveInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
//    Map<Long, Double> resolvedPrices = new HashMap<>();
//    for (Product product : products) {
//      Double price = internalPrices.get(product.getId());
//      if (price == null) {
//        price = thirdPartyPricesApi.fetchPrice(product.getId()); // 3rd party call
//      }
//      resolvedPrices.put(product.getId(), price);
//    }
//    return  ImmutableMap.copyOf(resolvedPrices);

    // BUG: se cheama trhird party de fiecare data, chiar daca avem deja pretul in internalPrices
    return products.stream().collect(ImmutableMap.toImmutableMap(Product::getId,
        product -> resolvePrice(internalPrices, product)));
    // return {product.id: internalPrices.get(product.id) ?? thirdPartyPricesApi.fetchPrice(product.id) for product in products}
    // return {product.id: resolvePrice(internalPrices, product) for product in products} 💖
  }

  private Double resolvePrice(Map<Long, Double> internalPrices, Product product) {
    if (internalPrices.containsKey(product.getId())) {
      return internalPrices.get(product.getId());
    }
    return thirdPartyPricesApi.fetchPrice(product.getId());

    // in python metoda asta ar fi scrisa ca:
    // return internalPrices.get(product.id) ?? thirdPartyPricesApi.fetchPrice(product.id)
  }

  // prices, usedCoupons = f(customer, products, internalPrices)

  private record Result(Map<Long, Double> finalPrices, List<Coupon> usedCoupons) {
  }
}

