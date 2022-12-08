package victor.training.fp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
public class StreamWrecks {
	private ProductRepo productRepo;


	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productToCount = getProductToCount(orders);
		List<Product> frequentProducts = selectFrequentProducts(productToCount);

		// change request: sa logezi daca avem > 100 de produse frecvente!!
		if (frequentProducts.size() > 100) {
			log.info("MARFA!");
		}

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		// GATA, da !
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

	@NotNull
	private static List<Product> selectFrequentProducts(Map<Product, Integer> productToCount) {
		List<Product> frequentProducts = productToCount.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();
		return frequentProducts;
	}

	@NotNull
	private static Map<Product, Integer> getProductToCount(List<Order> orders) {
		Map<Product, Integer> productToCount = orders.stream()
				.filter(o -> isRecent(o))
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
		return productToCount;
	}

	// "daca orderurile sunt recente (adica in plasate in ultimul an)
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
