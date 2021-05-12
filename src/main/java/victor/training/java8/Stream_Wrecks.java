package victor.training.java8;

import io.vavr.control.Either;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

// get the products frequently ordered during the past year


class ProductService {
	private ProductRepo productRepo;

//	public Either<String, Integer> method() {
//
//	}
	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productOrderCounts = countProductsOverLastYear(orders);

		List<Product> frequentProducts = productOrderCounts.entrySet().stream()
			.filter(e -> e.getValue() >= 10)
			.map(Entry::getKey)
			.collect(toList());

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
			.filter(Product::isNotDeleted)
			.filter(p -> !hiddenProductIds.contains(p.getId()))
			.collect(toList());
	}

	private Map<Product, Integer> countProductsOverLastYear(List<Order> orders) {
		return orders.stream()
			.filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
			.flatMap(o -> o.getOrderLines().stream())
			.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
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
	List<Long> getHiddenProductIds();
}
