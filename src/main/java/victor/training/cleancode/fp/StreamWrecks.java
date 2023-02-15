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

public class StreamWrecks {
	private ProductRepo productRepo;

	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> productItemsSold = orders.stream()
						.filter(StreamWrecks::isRecent)
						.flatMap(o -> o.getOrderLines().stream())
						.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
		List<Product> frequentProducts = productItemsSold.entrySet().stream()
						.filter(e -> e.getValue() >= 10)
						.map(Entry::getKey)
						.collect(toList());
		// if > 20 freq prod
		if (frequentProducts.size() > 20) {
			System.out.println("Send email");
		}
		List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
		return frequentProducts.stream()
						//            .filter(p -> !p.isDeleted())
						//            .filter(not(Product::isDeleted))
						.filter(Product::isActive)
						.filter(p -> !hiddenProductIds.contains(p.getId()))
						.collect(toList());
	}

	private static boolean isRecent(Order order) {
		return order.getCreationDate().isAfter(LocalDate.now().minusYears(1));
	}
}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

