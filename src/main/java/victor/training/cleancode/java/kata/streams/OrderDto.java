package victor.training.cleancode.java.kata.streams;

import victor.training.cleancode.java.kata.streams.Order.PaymentMethod;
import victor.training.cleancode.java.kata.streams.Order.Status;

import java.time.LocalDate;

// DON'T REFACTOR THIS
record OrderDto(double totalPrice,
                LocalDate creationDate,
                PaymentMethod paymentMethod,
                Status status) {

  public OrderDto(Order order) {
    this(order.total(),
        order.createdOn(),
        order.paymentMethod(),
        order.status());
  }
}
