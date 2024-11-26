package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

public class FunctionalChainsaw { // ... Massacre
	private final ProductRepo productRepo;

  public FunctionalChainsaw(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

	// const f = (param) => bla.bla;
	private static List<Product> getFrequentProducts(Map<Product, Integer> recentProductCounts) {
		return recentProductCounts
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();
	}

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		List<Order> recentActiveOrders = orders.stream()
				.filter(Order::isActive)
				.filter(Order::isRecent)
				.toList();
		Map<Product, Integer> recentProductCounts = recentActiveOrders.stream()
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));

		List<Product> frequentProducts = getFrequentProducts(recentProductCounts);
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}