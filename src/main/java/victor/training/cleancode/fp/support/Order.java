package victor.training.cleancode.fp.support;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Order {
  private Long id;
  private List<OrderLine> orderLines = List.of();
  private LocalDate creationDate;
  private LocalDate shipDate;
  private boolean active;
  private int price;

  public Long id() {
    return id;
  }

  public List<OrderLine> orderLines() {
    return orderLines;
  }

  public LocalDate creationDate() {
    return creationDate;
  }

  public Optional<LocalDate> shipDate() {
    return Optional.ofNullable(shipDate);
  }

  public boolean isActive() {
    return active;
  }

  public int price() {
    return price;
  }

  public Order setId(final Long id) {
    this.id = id;
    return this;
  }

  public Order setOrderLines(final List<OrderLine> orderLines) {
    this.orderLines = orderLines;
    return this;
  }

  public Order setCreationDate(final LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  public Order setShipDate(final LocalDate shipDate) {
    this.shipDate = shipDate;
    return this;
  }

  public Order setActive(final boolean active) {
    this.active = active;
    return this;
  }

  public Order setPrice(final int price) {
    this.price = price;
    return this;
  }

  public boolean isRecent() {
    return creationDate().isAfter(LocalDate.now().minusYears(1));
  }
}
