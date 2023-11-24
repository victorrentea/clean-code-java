package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
  private ProductRepo productRepo;

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
    Map<Product, Integer> countsByProducts = orders.stream()
        .filter(Order::isActive)
        .filter(this::isRecent)
        .flatMap(o -> o.getOrderLines().stream())
        .collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
    Stream<Product> oooooStreamInAVar = countsByProducts.entrySet().stream()
        .filter(e -> e.getValue() >= 10)
        .map(Entry::getKey)
        .filter(not(Product::isDeleted));
    if (oooooStreamInAVar.count() == 0) return List.of();
    Set<Long> hiddenProductIds = new HashSet<>(productRepo.getHiddenProductIds());
    return oooooStreamInAVar
        .filter(p -> !hiddenProductIds.contains(p.getId()))
        .collect(toList());
  }

  private boolean isRecent(Order o) {
    return o.getCreationDate().isAfter(LocalDate.now().minusYears(1));
  }
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

