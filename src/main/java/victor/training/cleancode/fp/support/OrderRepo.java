package victor.training.cleancode.fp.support;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface OrderRepo {
  List<Order> findAll();
  Stream<Order> findByActiveTrueAndCreationDateAfter(LocalDate date);
  Stream<Order> findByActiveTrue();
}
