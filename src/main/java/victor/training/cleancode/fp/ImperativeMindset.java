package victor.training.cleancode.fp;

import com.google.common.collect.ImmutableList;
import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
     //      final int[] sum = {0};

     //       AtomicInteger sum = new AtomicInteger();

     // var ref = new Object() {
     //       int sum = 0;
     //     };
//               .map(order => order.price)
//               .reduce((acc,price)=>acc+price,0);
//
//               .reduce((acc,order)=>acc+order.price,0);
     int sum = orders.parallelStream()
             .filter(Order::isActive)
//             .filter(o -> o.getPrice()!=null)
             .mapToInt(Order::getPrice)
             .sum();
     // 🍻
     //             .map(Order::getPrice)
     //             .reduce(0, Integer::sum);

     //                .mapToInt(Order::getPrice)
     //                .sum();
     System.out.println("Total: " + sum);
   }


   public void accumulateInList(List<Order> orders) {
     ImmutableList<Integer> prices =orders.stream()
          .filter(order -> order.isActive())
                  .map(Order::getPrice)
//                  .collect(Collectors.toList());
        .collect(ImmutableList.toImmutableList());

//          .forEach(order -> {// forEach e rau
//            // daca face side effects ce le puteai evita
//              prices.add(order.getPrice());
//          });
      System.out.println("Prices: " + prices);
   }
}
