package victor.training.cleancode.fp;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> recentlyOrderedProductCounts = orders.stream()
						.filter(StreamWrecks::orderIsRecent)
						.flatMap(o -> o.getOrderLines().stream())
						.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));

		List<Product> frequentProducts = recentlyOrderedProductCounts.entrySet().stream()
						.filter(e -> e.getValue() >= 10)
						.map(Entry::getKey)
						.collect(toList());

		// tinere, tu tine in variable Stream<> ca-o sa te arzi, ci List

//		if (frequentProducts.size() > 100) {
//			System.out.println("AM MULTE FRECVENTE!");
//		}

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // 1 single DB query => -1 sec runtime
		return frequentProducts.stream()
				// A mai secvential de citit
				.filter(p -> p.isActive())
				.filter(p -> !hiddenProductIds.contains(p.getId()))

				// B mai 'new-age', o tzara mai eficient >> Masoar-o
//				.filter(p -> p.isActive() && !productRepo.getHiddenProductIds().contains(p.getId()))
				.collect(toList());
	}

	private static boolean orderIsRecent(Order o) {
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
