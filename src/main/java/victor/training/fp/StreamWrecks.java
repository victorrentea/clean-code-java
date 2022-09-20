package victor.training.fp;

import victor.training.fp.model.Order;
import victor.training.fp.model.OrderLine;
import victor.training.fp.model.Product;
import victor.training.fp.model.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		return orders.stream()
				.filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.filter(p -> !p.isDeleted())
				.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
				.collect(toList());
	}
}


