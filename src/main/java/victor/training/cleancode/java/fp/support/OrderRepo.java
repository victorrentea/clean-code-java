package victor.training.cleancode.java.fp.support;


import java.util.stream.Stream;

public interface OrderRepo  {
  Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
}
