package victor.training.cleancode.fp;

import org.jetbrains.annotations.NotNull;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
  private ProductRepo productRepo;

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
    Map<Product, Integer> recentProductCounts = getRecentProductCounts(orders);
    List<Product> frequentProducts = getFrequentProducts(recentProductCounts);

    // CR send us an email if > 100 products are frequent
    if (frequentProducts.size() > 100) {
      System.out.println("Send kafka");
    }

    List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
    return frequentProducts.stream()
//            .filter(p -> !p.isDeleted())
            .filter(Product::isActive)
//            .filter(not(Product::isDeleted))
            .filter(p -> !hiddenProductIds.contains(p.getId()))
            .collect(toList());
  }

  @NotNull
  private static List<Product> getFrequentProducts(Map<Product, Integer> recentProductCounts) {
    return recentProductCounts.entrySet().stream()
            .filter(e -> e.getValue() >= 10)
            .map(Entry::getKey)
            .toList();
  }

  // fun getRecentProductCounts(List<Order> orders) = orders.stream()
  private static Map<Product, Integer> getRecentProductCounts(List<Order> orders) {
    return orders.stream()
            .filter(Order::isRecent)
            .flatMap(o -> o.getOrderLines().stream())
            .collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
  }

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

