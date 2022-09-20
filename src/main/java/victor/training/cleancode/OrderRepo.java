package victor.training.cleancode;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.cleancode.model.Order;

import java.util.stream.Stream;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
}
