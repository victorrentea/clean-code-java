package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
  private ProductRepo productRepo;

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
    Map<Product, Integer> recentProducts = getRecentProducts(orders);
    List<Product> popularProducts = filterPopularProducts(recentProducts);
    List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
    return popularProducts.stream()
        .filter(not(Product::isDeleted))
        .filter(p -> !hiddenProductIds.contains(p.getId()))
        .collect(toList());
  }

  // DOAMNE FERESTE. NU in java. da in Kt, scala, ts
  static Function<Map<Product, Integer>, List<Product>> f =
      map -> map.entrySet().stream()
          .filter(e -> e.getValue() >= 10)
          .map(Entry::getKey)
          .toList();

  // PURE FUNCTION
  private static List<Product> filterPopularProducts(Map<Product, Integer> recentProducts) {
    return recentProducts.entrySet().stream()
        .filter(e -> e.getValue() >= 10)
        .map(Entry::getKey)
        .toList();
  }

  // PURE FUNCTION
  private static Map<Product, Integer> getRecentProducts(List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
        .filter(StreamWrecks::isRecent)
        .flatMap(o -> o.getOrderLines().stream())
        .collect(groupingBy(OrderLine::getProduct,
            summingInt(OrderLine::getItemCount)));
  }


  private static boolean isRecent(Order order) {
    return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
  }
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

