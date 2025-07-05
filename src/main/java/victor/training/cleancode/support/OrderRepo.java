package victor.training.cleancode.support;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface OrderRepo {
  List<Order> findAll();
  List<Order> findByActiveTrueAndCreationDateAfter(LocalDate date);
  Stream<Order> findByActiveTrue();
}
