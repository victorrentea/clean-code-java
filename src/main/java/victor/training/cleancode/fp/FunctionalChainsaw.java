package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class FunctionalChainsaw { // ... Massacre
	private final ProductRepo productRepo;

  public FunctionalChainsaw(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

	private static Map<Product, Integer> getProductCounts(List<Order> orders) {
		return orders.stream()
				.filter(Order::isActive)
				.filter(Order::isRecent)

//				.flatMap(o -> o.orderLines().stream())

				.map(Order::orderLines)
				.flatMap(List::stream)

				.collect(groupingBy(OrderLine::product,
						summingInt(OrderLine::itemCount)));
	}

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productCounts = getProductCounts(orders);
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // 1 call not 1000 -5 seconds runtime
		List<Product> products = productCounts.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)

//				.filter(p -> !p.isDeleted())
				.filter(Predicate.not(Product::isDeleted))

				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());

//		var nonHiddenIds = productRepo.selectNonHiddenProductsFrom(products.stream().map(Product::getId).toList());

		return products;
	}

}