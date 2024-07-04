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

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		List<Product> frequentProducts = getProductToQuantity(orders).entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p1 -> !hiddenProductIds.contains(p1.getId()))
				.collect(toList());
	}

	private Map<Product, Integer> getProductToQuantity(List<Order> orders) {
		return orders.stream()
				.filter(Order::isActive)
				.filter(Order::placedOverTheLastYear)
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));
	}

}