package victor.training.cleancode.fp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> recentProductCounts = orders.stream()
						.filter(StreamWrecks::isRecent)
						.flatMap(o -> o.getOrderLines().stream())
						.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));

		List<Product> topProducts = recentProductCounts.entrySet().stream()
						.filter(e -> e.getValue() >= 10)
						.map(Entry::getKey)
						.toList();


		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();

		return topProducts.stream()
						.filter(p -> !p.isDeleted())
						.filter(p -> !hiddenProductIds.contains(p.getId()))
						.collect(toList());
	}

	private static boolean isRecent(Order order) {
		return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
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
