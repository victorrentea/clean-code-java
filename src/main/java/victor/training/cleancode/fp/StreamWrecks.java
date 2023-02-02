package victor.training.cleancode.fp;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
	public static final int THRESHOLD_FOR_FREQUENT_PRODUCT = 10;
	private ProductRepo productRepo;

//	public static void main(String[] args) {
//		new StreamWrecks().method("getFrequentOrderedProducts");
//	}
//	public void method(String omg) {
//		StreamWrecks.class.getMethod(omg).invoke("a");
//	}

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		final int MIN_COUNT_FOR_FREQUENT = THRESHOLD_FOR_FREQUENT_PRODUCT;
		Map<Product, Integer> productToItemCounts = getProductCounts(orders);

		List<Product> frequentProducts = productToItemCounts.entrySet().stream()
						.filter(e -> e.getValue() >= THRESHOLD_FOR_FREQUENT_PRODUCT)
						.map(Entry::getKey)
						.collect(toList());

		// if daca sunt peste 50 de produse vrecvente da in kafka un event
//		if (frequentProducts.count() > 50) System.out.println("Kafka send");
//		frequentProducts.forEach(System.out::println);

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
//				.filter(p -> !p.isDeleted())
//				.filter(Product::isActive)
				.filter(not(Product::isDeleted))
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

	@NotNull
	private static Map<Product, Integer> getProductCounts(List<Order> orders) {
		// e fain asa
		return orders.stream()
						.filter(StreamWrecks::isRecent)
						.map(Order::getOrderLines)
						.flatMap(Collection::stream)
						.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private static boolean isRecent(Order o) {
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
