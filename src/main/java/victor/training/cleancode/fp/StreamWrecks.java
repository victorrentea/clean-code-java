package victor.training.cleancode.fp;

import org.jetbrains.annotations.NotNull;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
	private ProductRepo productRepo;

	// they tried to make Java look like a functional programming language (Scala,Kotlin,Clojure,Haskell)
	// to make it more "modern" and "cool" and appealing to those in competing language
	// fun f() =
	@NotNull
	private static Map<Product, Integer> getRecentBoughtProducts(List<Order> orders) {
		return orders.stream()
						.filter(StreamWrecks::isRecent)
						// A
						.flatMap(o -> o.getOrderLines().stream())
						// B
						//						.map(Order::getOrderLines)
						//						.flatMap(Collection::stream)

						.collect(groupingBy(OrderLine::getProduct,
										summingInt(OrderLine::getItemCount)));
	}

	private static boolean isRecent(Order order) {
		return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
	}

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {

		List<Product> frequentProducts = getRecentBoughtProducts(orders)
						.entrySet()
						.stream()
						.filter(e -> e.getValue() >= 10)
						.map(Entry::getKey)
						.toList();
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
						// A easier to read
						.filter(not(Product::isDeleted))
						.filter(p -> !hiddenProductIds.contains(p.getId()))

						// B
						//				.filter(not(Product::isDeleted).and(p -> !hiddenProductIds.contains(p.getId())))
						.collect(toList());
	}
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

