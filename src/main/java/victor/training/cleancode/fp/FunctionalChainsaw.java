package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class FunctionalChainsaw/*Massacre*/ {
	private final ProductRepo productRepo;
	private final OrderRepo orderRepo;

	public List<Product> getHotProducts() {
    List<Long> hiddenProductIds = productRepo.getHiddenProductIds();

    Map<Product, Integer> recentlyOrderedProductCount = orderRepo.findAll().stream()
        // 100M
        .filter(Order::isActive)
        .filter(Order::createdWithinTheLastMonth)
        // 10000k 0.1% => stupid =>> SQL WHERE are back!
        // complex business filtering -> Java
        // massive (eg time-based) filtering -> WHERE >>> extremo: CASE WHEN
        .flatMap(o -> o.orderLines().stream())
        .collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));

    List<Product> hotProducts = recentlyOrderedProductCount.entrySet().stream()
        .filter(e -> e.getValue() >= 10)
        .map(Entry::getKey)
        .toList();

    return hotProducts.stream()
        .filter(Predicate.not(Product::isDeleted))
        .filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}