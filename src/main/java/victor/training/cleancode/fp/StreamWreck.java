package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWreck {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productToItemCount = getProductToItemCount(orders);

		// Iterator, InputStream
		// challenge in review any Stream<> as method return value or variable type.
		List<Product> frequentProducts = productToItemCount.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();

		//risk:consume twice
		if (frequentProducts.size()>100) {
			throw new IllegalStateException("Too many products");
		}

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); //1 DB hit only !!
		return frequentProducts.stream()
				.filter(not(Product::isDeleted))
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.toList();
	}



	private Map<Product, Integer> getProductToItemCount(List<Order> orders) {
    return orders.stream()
				.filter(Order::isActive)
				.filter(order -> order.isRecent(LocalDate.now()))
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private boolean isRecent(Order order) {
		return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
	}

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

