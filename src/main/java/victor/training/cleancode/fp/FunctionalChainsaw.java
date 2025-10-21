package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.OrderRepo;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class FunctionalChainsaw/*Massacre*/ {
	private final ProductRepo productRepo;
	private final OrderRepo orderRepo;

	public List<Product> getHotProducts() {
    // nu mai bine un WHERE?
//    Map<Product, Integer> orderedProductCounts // pt creier
//    Map<Product, Integer> orderedProductCounts = orderRepo.findAll().stream()
    // 10 M
    Map<Product, Integer> orderedProductCounts = orderRepo.findByActiveTrueAndCreationDateAfter(LocalDate.now().minusMonths(1))
        // 1000
        .stream()
//        .filter(Order::isActive)
//        .filter(Order::isRecent)
        .flatMap(o -> o.orderLines().stream())
        .collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));

    List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // pt performance++

    return orderedProductCounts.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.filter(p -> !p.isDeleted())
        .filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}