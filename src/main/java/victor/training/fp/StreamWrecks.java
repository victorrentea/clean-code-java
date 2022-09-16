package victor.training.fp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

@Slf4j
public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productCount = getProductCounts(orders);
		log.trace("look: {}", productCount);
		List<Product> frequentProducts = productCount.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.collect(toList());

		// tomorrow, git blame : 9:31 PM
		if (frequentProducts.size() > 10) {
			System.out.println("Hooray, a good customer!");
		}

		// Avoid keeping functions in variables
//		Predicate<Product> hehe = p -> !productRepo.getHiddenProductIds().contains(p.getId());

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); //1 REPO call, not N
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p	 -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

	private static Map<Product, Integer> getProductCounts(List<Order> orders) {
		return orders.stream()
				.filter(StreamWrecks::isRecent)
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private static boolean isRecent(Order o) {
		return o.getCreationDate().isAfter(now().minusYears(1));
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
