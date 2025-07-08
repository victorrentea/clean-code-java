package victor.training.cleancode;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import victor.training.cleancode.support.*;

import java.time.LocalDate;
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
		return pure4Dan(orderRepo.findAll(), productRepo.getHiddenProductIds(), LocalDate.now());
	}

	// pure function = no side effects, no injected dependencies, no change to mutable state
	@VisibleForTesting // = sonar will fail if this method is called from /src/main/java
	static // proof that this func does NOT use and injected dep => it MIGHT be pure
	List<Product> pure4Dan(List<Order> orders, List<Long> hiddenProductIds, LocalDate now) {
		var recentProductCount = orders.stream()
				.filter(Order::isActive)// TODO WHERE ftw
				.filter(order -> order.withinTheLastMonth(now)) // OOP
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));
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