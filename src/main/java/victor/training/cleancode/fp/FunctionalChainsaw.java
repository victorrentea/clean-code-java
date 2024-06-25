package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class FunctionalChainsaw { // ... Massacre
	private final ProductRepo productRepo;

  public FunctionalChainsaw(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productsSoldOverLastYear = orders.stream()
				.filter(Order::isActive)
				.filter(Order::isPlacedWithinLastYear)
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));

		// variables of type Stream<> should be avoided if the collection fits in memory
//		Stream<Product> frequentProducts = productsSoldOverLastYear.entrySet().stream()
		List<Product> frequentProducts = productsSoldOverLastYear.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.toList();

//		if (frequentProducts.count() > 10) {
//			System.out.println("Too many frequent products. Limiting to 10");
//		}

		List<Long> hiddenProductIds = productRepo.getHiddenProductIds(); // 1 single DB call
		return frequentProducts.stream()
				.filter(p -> !p.isDeleted())
				.filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}