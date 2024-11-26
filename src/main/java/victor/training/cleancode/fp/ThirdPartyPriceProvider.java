package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ThirdPartyPricesApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Slf4j
//@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ThirdPartyPriceProvider {
  private final ThirdPartyPricesApi thirdPartyPricesApi;

  public Map<Long, Double> resolvePrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.getId());
      }
      initialPrices.put(product.getId(), price);
//      product.setTemporaryPrice(price); // temporary field code smell!!!!!!!! NEVER@*&^!$(#!^@$&%^!@*^$%
//      internalPrices.put(product.getId(), price); // mutating parameter state - code smell
    }
    return initialPrices;
  }
}
