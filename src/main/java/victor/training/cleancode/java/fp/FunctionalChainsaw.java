package victor.training.cleancode.java.fp;

import victor.training.cleancode.java.fp.support.Order;
import victor.training.cleancode.java.fp.support.OrderLine;
import victor.training.cleancode.java.fp.support.Product;
import victor.training.cleancode.java.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

public class FunctionalChainsaw { // ... Massacre
	private final ProductRepo productRepo;

  public FunctionalChainsaw(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		return orders.stream()
				.filter(Order::isActive)
				.filter(o -> o.creationDate().isAfter(LocalDate.now().minusYears(1)))
				.flatMap(o -> o.orderLines().stream())
				.collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.filter(p -> !p.isDeleted())
				.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
				.collect(toList());
	}
}