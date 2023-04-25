package victor.training.cleancode.fp;

import org.jetbrains.annotations.NotNull;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
  private ProductRepo productRepo;

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
    Map<Product, Integer> recentProductCounts = countOfRecentProducts(orders);
    List<Product> hotProducts = selectHotProducts(recentProductCounts);

    // CR urgent: sa dai mai lu bizu cand ai > 100 hot products
    if (hotProducts.size() > 100) {
      System.out.println("MAIL LA BIZU'");
    }
    List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
    return hotProducts.stream()
//            .filter(p -> !p.isDeleted())
            .filter(not(Product::isDeleted))
            .filter(p -> !hiddenProductIds.contains(p.getId()))
            .collect(toList());
  }

  @NotNull
  private static List<Product> selectHotProducts
          (Map<Product, Integer> recentProductCounts) {
    return recentProductCounts.entrySet().stream()
            .filter(e -> e.getValue() >= 10)
//            .filter(e => e.getValue() >= 10) << asta in FE
//            .filter(function(e) {return e.getValue() >= 10})
            .map(Entry::getKey)
            .collect(toList());
//  const f = p => treaba
//  function f(p) {

  }

  @NotNull
  private static Map<Product, Integer> countOfRecentProducts(List<Order> orders) {
    return orders.stream()
            .filter(Order::isActive)
            .filter(Order::isRecent)
            .flatMap(o -> o.getOrderLines().stream())
            .collect(groupingBy(OrderLine::getProduct,
                    summingInt(OrderLine::getItemCount)));
  }

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

