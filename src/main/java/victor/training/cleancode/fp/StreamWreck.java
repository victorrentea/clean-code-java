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

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWreck {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		LocalDate now = LocalDate.now();
		Map<Product, Integer> productToCounts = getProductToCounts(orders, now);

		List<Product> mostBoughtProducts = productToCounts
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();

		//		log.trace("Most bought products: " + mostBoughtProducts);
    return mostBoughtProducts.stream()
				.filter(not(Product::isDeleted))
				.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
				.collect(toList());
	}

	private Map<Product, Integer> getProductToCounts(List<Order> orders, LocalDate now) {
		Map<Product, Integer> productToCounts = orders.stream()
//				.filter(order -> order.isActive() && isRecent(order, now)) // A
				.filter(Order::isActive) // this is more readable
				.filter(order -> order.isRecent(now))
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));
		//		log.trace("Product to counts: " + productToCounts);
		return productToCounts;
	}

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

