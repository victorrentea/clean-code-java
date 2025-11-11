package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class FunctionalChainsaw/*Massacre*/ {
	private final ProductRepo productRepo;
	private final OrderRepo orderRepo;

  public List<Product> getHotProducts() {
    List<Long> hiddenProductIds = productRepo.getHiddenProductIds();
    // FP maniac averse to SQL = prost
    Map<Product, Integer> productCounts = orderRepo.findAll().stream()
//    Map<Product, Integer> productCounts = orderRepo.findAllByDateAfter(now-1 luna).stream()
        .filter(Order::isActive)
        .filter(Order::isWithinLastMonth) // lasa <0.1% din date => in  WHERE per favore!!!
        .flatMap(o -> o.orderLines().stream())
        .collect(groupingBy(OrderLine::product, summingInt(OrderLine::itemCount)));

    return productCounts
				.entrySet()
				.stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey)
				.filter(p -> !p.isDeleted())
        .filter(p -> !hiddenProductIds.contains(p.getId()))
				.collect(toList());
	}

}