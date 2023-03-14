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

public class StreamWrecks {
	private ProductRepo productRepo;
	String s;
	public List<Product> getFrequentOrderedProducts(List<Order> orders) {
		Map<Product, Integer> recentProductCounts = orders.stream()
						.filter(Order::isRecent)
						.flatMap(o -> o.getOrderLines().stream())
						.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
		Stream<Product> frequentProducts = recentProductCounts.entrySet().stream()
						.filter(e -> e.getValue() >= 10)
						.map(Entry::getKey);

		// CR: sa ne dai un mail daca sunt > 100 de produse frecvente
		if (frequentProducts.count() > 100) { // 'count' op final
			System.out.println("mail!");
		}

		return frequentProducts // runtime exception
						.filter(p -> !p.isDeleted())
						.filter(p -> !productRepo.getHiddenProductIds().contains(p.getId()))
						.collect(toList());
	}

}


//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV

