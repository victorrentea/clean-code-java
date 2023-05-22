package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		return orders.stream()
				.filter(Order::isActive)
				.filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.filter(p -> !p.isDeleted())
				.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
				.toList(); // immutable collection ftw
	}

//	Predicate<Order> createdBetween(LocalDate low, LocalDate high) {
//		return o-> o.getCreationDate().isAfter(low) && ...
//	}
	// order1.createdBetween(low, high)
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

