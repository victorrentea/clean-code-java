package victor.training.java8;

import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

// get the products frequently ordered during the past year


class ProductService {
	private ProductRepo productRepo;


	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		// mark a variable as lazy
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();

		Predicate<Product> notHidden = p -> !hiddenProductIds.contains(p.getId());

		return getProductOrderCounts(orders)
				.stream()
				.filter(ProductCount::isFrequent)
				.map(ProductCount::getProduct)
				.filter(Product::isNotDeleted)
				.filter(notHidden)
				.collect(toList());
	}

	private List<ProductCount> getProductOrderCounts(List<Order> orders) {
		return orders.stream()
			.filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
			.flatMap(o -> o.getOrderLines().stream())
			.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)))
			.entrySet()
			.stream()
			.map(e -> new ProductCount(e.getKey(), e.getValue()))
			.collect(toList());
	}
}
@Value
class ProductCount {
	 Product product;
	 int count;

	public boolean isFrequent() {
		return getCount() >= 10;
	}
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
@Data
class Product {
	private Long id;
	private boolean deleted;

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
//	@Cacheable("thread-local-hid-product-cache")
	List<Long> getHiddenProductIds();
}
