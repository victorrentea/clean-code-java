package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.OrderRepo;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductControlApi;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class E2_FunctionalChainsaw/*Massacre*/ {
  private final ProductControlApi productControlApi;
  private final OrderRepo orderRepo;

  //  @Transactional(readonly=true)
  public List<Product> getHotProducts() {
//    var productCounts = orderRepo.findAll().stream()
//        .filter(Order::isActive)
//        .filter(Order::wasPlacedWithingTheLastMonth) // power <1% > WHERE
    var productCounts = orderRepo.findByActiveTrueAndCreationDateAfter(
            LocalDate.now().minusMonths(1)
        )
        .flatMap(o -> o.orderLines().stream())
        .collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));
    var frequentProducts = productCounts.entrySet().stream()
        .filter(e -> e.getValue() >= 10)
        .map(Entry::getKey)
        .toList();
    var hiddenProductIds = productControlApi.getHiddenProductIds();
    return frequentProducts.stream()
        .filter(not(Product::isDeleted))
        .filter(p -> !hiddenProductIds.contains(p.getId()))
        .collect(toList());
  }

}