package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> recentProductCounts = aggregateRecentProductCounts(orders);
		List<Product> frequentProducts = selectFrequentProducts(recentProductCounts);
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
						//				.filter(p -> !p.isDeleted())
						.filter(not(Product::isDeleted))
						.filter(p -> !hiddenProductIds.contains(p.getId()))
						.collect(toList());
	}

	//	fun selectFrequentProducts( recentProductCounts: Map<Product, Integer>) =
	//					recentProductCounts.entrySet().stream();
	private static List<Product> selectFrequentProducts(Map<Product, Integer> recentProductCounts) {
		return recentProductCounts.entrySet().stream()
						.filter(e -> e.getValue() >= 10)
						.map(Entry::getKey)
						.collect(toList());
	}

	private static Map<Product, Integer> aggregateRecentProductCounts(List<Order> orders) {
		return orders.stream()
						.filter(Order::isActive)
						.filter(Order::isRecent)
						.flatMap(o -> o.getOrderLines().stream())
						.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

