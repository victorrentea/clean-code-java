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

  public FunctionalChainsaw(final ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  public List<Product> getFrequentOrderedProducts(final List<Order> orders) {
//		CompletableFuture.failedFuture(new RuntimeException());
//		CompletableFuture.completedFuture("aaa");

//		Mono.failed(new RuntimeException());
		// Mono.just("aaa");


		final List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		final Map<Product, Integer> boughtProducts = orders.stream()
				.filter(Order::isActive)
				.filter(Order::isRecent)
				.flatMap(order -> order.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));
		final List<Product> frequentProducts = boughtProducts.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
//				.filter(not(Product::isDeleted))
//				.filter(Product::isNotDeleted)
//				.filter(Product::isActive)
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}