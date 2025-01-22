package victor.training.cleancode.java.kata.streams;

// DON'T REFACTOR THIS
public class OrderMapper {
  public OrderDto toDto(Order order) {
    return new OrderDto(
        order.total(),
        order.createdOn(),
        order.paymentMethod(),
        order.status());
  }
}
