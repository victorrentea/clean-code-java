package victor.training.cleancode.fp.support;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Order {
  private Long id;
  private List<OrderLine> orderLines;
  private LocalDate creationDate;
  private LocalDate shipDate;
  private boolean active;
  private int price;

  public Long getId() {
    return id;
  }

  public List<OrderLine> getOrderLines() {
    return orderLines;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public Optional<LocalDate> shipDate() {
    return Optional.ofNullable(shipDate);
  }

  public boolean isActive() {
    return active;
  }

  public int getPrice() {
    return price;
  }

  public Order setId(Long id) {
    this.id = id;
    return this;
  }

  public Order setOrderLines(List<OrderLine> orderLines) {
    this.orderLines = orderLines;
    return this;
  }

  public Order setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  public Order setShipDate(LocalDate shipDate) {
    this.shipDate = shipDate;
    return this;
  }

  public Order setActive(boolean active) {
    this.active = active;
    return this;
  }

  public Order setPrice(int price) {
    this.price = price;
    return this;
  }

  public boolean hasPremiumProducts() {
    return getOrderLines().stream().anyMatch(line -> line.product().isPremium());
  }

  public boolean isRecent() {
    return getCreationDate().isAfter(LocalDate.now().minusYears(1));
  }
}
