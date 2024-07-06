package victor.training.cleancode.kata.streams;

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
