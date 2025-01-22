package victor.training.cleancode.java.kata.streams;

// DON'T REFACTOR THIS
public record OrderLine(Product product, int count, boolean isSpecialOffer) {
  public OrderLine() {
    this(new Product("dummy"), 0);
  }

  public OrderLine(Product product, int items) {
    this(product, items, false);
  }

  public OrderLine specialOffer(boolean specialOffer) {
    return new OrderLine(product, count, specialOffer);
  }
}
