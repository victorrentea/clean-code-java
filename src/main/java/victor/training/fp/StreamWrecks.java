package victor.training.fp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

@Slf4j
public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		List<Product> frequentProducts = getRecentBoughtProducts(orders)
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.collect(toList());
		log.debug("Lista de produse " + frequentProducts);
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // 1 data retea

		return frequentProducts.stream() // 100 de produse in lista
				.filter(p -> !p.isDeleted())
				.filter(not(Product::isDeleted))
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

	private Map<Product, Integer> getRecentBoughtProducts(List<Order> orders) {
		return orders.stream()
				.filter(this::isRecent)
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct,
						summingInt(OrderLine::getItemCount)));
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
}

@Data
class Order {
	private Long id;
	private List<OrderLine> orderLines;
	private LocalDate creationDate;
	private boolean active;
	private int price;
}

@Data
class OrderLine {
	private Product product;
	private int itemCount;
}

interface ProductRepo {
	List<Long> getHiddenProductIds();
}
