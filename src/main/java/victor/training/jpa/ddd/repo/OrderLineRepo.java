package victor.training.jpa.ddd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.jpa.ddd.model.OrderLine;

public interface OrderLineRepo extends JpaRepository<OrderLine, Long> {
}
