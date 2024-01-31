package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamWreck {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productToItemCount = orders.stream()
				.filter(Order::isActive)
				.filter(Order::isRecent)
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));

		// Iterator, InputStream
		// challenge in review any Stream<> as method return value or variable type.
		Stream<Product> frequentProducts = productToItemCount.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey);

		//risk:consume twice
		if (frequentProducts.count()>100) {
			throw new IllegalStateException("Too many products");
		}

		return frequentProducts
				.filter(p -> !p.isDeleted())
				.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
				.collect(toList());
	}

	private boolean isRecent(Order order) {
		return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
	}

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

