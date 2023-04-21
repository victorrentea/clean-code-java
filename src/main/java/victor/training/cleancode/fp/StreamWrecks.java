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

  @NotNull
  private static List<Product> frequentProducts(List<Order> orders) {
    return extractRecentProductsBought(orders).entrySet().stream()
            .filter(e -> e.getValue() >= 10)
            .map(Entry::getKey)
            .collect(toList());
  }

  @NotNull
  private static Map<Product, Integer> extractRecentProductsBought(List<Order> orders) {
    return orders.stream()
            .filter(Order::isActive)
            .filter(Order::isRecent)
            .flatMap(o -> o.getOrderLines().stream())
            .collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
  }

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
    List<Product> frequentProducts = frequentProducts(orders);

    // send a kafka if we found > 100 freq prod
    if (frequentProducts.size() > 100) {
      System.out.println("Send kafka");
    }

    List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
    return frequentProducts.stream()
            //            .filter(p -> !p.isDeleted())
            .filter(not(Product::isDeleted))
            .filter(p -> !hiddenProductIds.contains(p.getId()))
            .collect(toList());
  }

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

