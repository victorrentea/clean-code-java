package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.support.*;

import java.util.List;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

// 0TODO 1
@RequiredArgsConstructor
public class /*Functional*/Chainsaw/*..BrainMassacre*/ {
	public static final int PRODUCT_COUNT_THRESHOLD = 10;
	private final ProductRepo productRepo;
	private final OrderRepo orderRepo;

	public List<Product> getHotProducts() {
		var recentProductCount = orderRepo.findAll().stream()
				.filter(Order::isActive)// TODO WHERE ftw
				.filter(Order::withinTheLastMonth) // OOP
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));
		var hiddenProductIds = productRepo.getHiddenProductIds();
		var frequentProducts = recentProductCount.entrySet()
				.stream()
				.filter(e -> e.getValue() >= PRODUCT_COUNT_THRESHOLD)
				.map(Entry::getKey)
				.toList();
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}