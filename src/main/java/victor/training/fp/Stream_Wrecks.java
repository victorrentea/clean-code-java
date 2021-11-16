package victor.training.fp;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

// get the products frequently ordered during the past year


class ProductService {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productCounts = orders.stream()
			.filter(this::isRecent) // use-case specific logic. //Only I consider orders to be recent if < 1 year old
			.flatMap(o -> o.getOrderLines().stream())
			.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));

		List<Product> popularProducts = productCounts.entrySet().stream()
			.filter(e -> e.getValue() >= 10)
			.map(Entry::getKey)
			.toList();

		if (popularProducts.size() > 100) {
			System.out.println("I have many prop");
		}

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // 1 single call do DB

		return popularProducts.stream()



			.filter(p -> p.isActive())
			.filter(p -> !p.isDeleted())
			.filter(not(Product::isDeleted))

				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
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
