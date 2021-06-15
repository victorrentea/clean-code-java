package victor.training.java8;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

// get the products frequently ordered during the past year


class ProductService {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productCounts = countRecentsProducts(orders);

		List<Product> frequentProducts = productCounts.entrySet().stream()
			.filter(e -> e.getValue() >= 10)
			.map(Entry::getKey)
			.collect(toList());

//				.filter(product -> !product.isDeleted())
//				.filter(not(Product::isDeleted))
//				.filter(Product::isActive)

		List<Long> hiddenIds = productRepo.getHiddenProductIds();

		return frequentProducts.stream()
				.filter(Product::isNotDeleted)
				.filter(p -> !hiddenIds.contains(p.getId()))
				.collect(toList());
	}

	private Map<Product, Integer> countRecentsProducts(List<Order> orders) {
		return orders.stream()
			.filter(this::isRecent)
			.flatMap(o -> o.getOrderLines().stream())
			.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private boolean isRecent(Order o) {
		return o.getCreationDate().isAfter(LocalDate.now().minusYears(1));
	}
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
@Data
class Product {
	private Long id;
	private boolean deleted;

	public boolean isActive() {
		return !deleted;
	}

	public boolean isNotDeleted() {
		return !deleted;
	}
}

@Data
class Order {
	private Long id;
	private List<OrderLine> orderLines;
	private LocalDate creationDate;
}

@Data
class OrderLine {
	private Product product;
	private int itemCount;
}

interface ProductRepo {
	List<Long> getHiddenProductIds();
}