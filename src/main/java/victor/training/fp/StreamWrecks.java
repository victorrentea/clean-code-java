package victor.training.fp;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> numberOfItemsPerProduct = getNumberOfItemsPerProduct(orders);

		// avoid keeping variables of type Stream because they can be mistakenly consumed
		List<Product> frequentProducts = numberOfItemsPerProduct.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.collect(toList());

		// tomorrow...
//		if (frequentProducts.count() > 10) {
//			System.out.println("The client is regular");
//		}

		//careful with functions in variables!
//		Predicate<Product> done = p -> !productRepo.getHiddenProductIds().contains(p.getId());
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // only 1 DB call.
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

	private static Map<Product, Integer> getNumberOfItemsPerProduct(List<Order> orders) {
		return orders.stream()
				.filter(StreamWrecks::isRecent)
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private static boolean isRecent(Order o) { // not reusable at all. it's just my perspective on this use case
		// NOT move it in the Order
//		String str = o.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MMM-dd"));
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
