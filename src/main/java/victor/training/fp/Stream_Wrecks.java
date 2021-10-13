package victor.training.fp;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collector;

import static java.util.function.Predicate.not;
import static victor.training.fp.X.toList;
import static java.util.stream.Collectors.*;

// get the products frequently ordered during the past year


class ProductService {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // loop invariant
		return getFrequentProducts(orders).stream()
			.filter(not(Product::isDeleted))
			.filter(not(p -> hiddenProductIds.contains(p.getId())))
			.collect(toList());
	}

	private List<Product> getFrequentProducts(List<Order> orders) {
		return getRecentProductCounts(orders)
			.entrySet().stream()
			.filter(e -> e.getValue() >= 10)
			.map(Entry::getKey)
			.collect(toList());
	}

	private Map<Product, Integer> getRecentProductCounts(List<Order> orders) {
		return orders.stream()
			.filter(this::isRecent)
			.flatMap(o -> o.getOrderLines().stream())
			.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private boolean isRecent(Order order) {
		return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
	}
}

 class X {
	public static <T>
	Collector<T, ?, List<T>> toList() {
		return null;
	}
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
@Data
class Product {
	private Long id;
	private boolean deleted;
}

@Data
class Order {
	private Long id;
	private List<OrderLine> orderLines;
	private LocalDate creationDate;
	private boolean active;
}

@Data
class OrderLine {
	private Product product;
	private int itemCount;
}

interface ProductRepo {
	List<Long> getHiddenProductIds();
}
