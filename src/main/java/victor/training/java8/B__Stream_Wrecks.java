package victor.training.java8;

import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

// get the products frequently ordered during the past year

@Value
class ProductOrderCount {
	Product product;
	int orderCount;
}

class ProductService {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		List<ProductOrderCount> products = computeProductCounts(orders);
			List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		List<Integer> list = IntStream.range(1, 100).boxed().collect(toList());
		list.forEach(n -> {
//				sad
//					sa
		});
		return products.stream()
				.filter(this::passesContraints)
				.map(ProductOrderCount::getProduct)
				.filter(Product::isNotDeleted)
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());

//			N+1 queries/hhtp calls  -- hibernate shines in here. Lazy load collections children.
	}

	private boolean passesContraints(ProductOrderCount e) {
		//					if (styff)
//						if more stuff
//						for
//							switch
		return e.getOrderCount() >= 10;
	}

	private List<ProductOrderCount> computeProductCounts(List<Order> orders) {
		List<OrderLine> orderLines = orders.stream()
			.filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
			.flatMap(o -> o.getOrderLines().stream())
			.collect(toList());

		Map<Product, Integer> productOrderCount =
			orderLines.stream().collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));

		return productOrderCount.entrySet().stream()
			.map(e -> new ProductOrderCount(e.getKey(), e.getValue()))
			.collect(toList());
	}
}
// on a Map, if you only use .keySet or .entrySet  --> you are degenerating that map.
// making func of that map.

// iterating over a map is making fun of it.

// you are really missing a class WHAT?!
// List<Stuff> you need. What stuff ?



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
	List<Long> getHiddenProductIds();
}
