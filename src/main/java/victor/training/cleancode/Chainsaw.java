package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.support.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

// TODO 1
@RequiredArgsConstructor
public class /*Functional*/Chainsaw/*..BrainMassacre*/ {
	private final ProductRepo productRepo;
	private final OrderRepo orderRepo;

	public List<Product> getHotProducts() {
		return orderRepo.findAll().stream()
				.filter(Order::isActive)
				.filter(o -> o.creationDate().isAfter(LocalDate.now().minusMonths(1)))
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.filter(p -> !p.isDeleted())
				.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
				.collect(toList());
	}
}