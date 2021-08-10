package victor.training.jpa.ddd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.jpa.ddd.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
