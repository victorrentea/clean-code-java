package victor.training.jpa.ddd.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ORDERS")
public class Order {
   @Id
   @GeneratedValue
   private Long id;

   private LocalDateTime createDate = LocalDateTime.now();

   @OneToMany
   @JoinColumn
   @Setter(AccessLevel.NONE)
   @ToString.Exclude
   private Set<OrderLine> orderLines = new HashSet<>();

   private BigDecimal totalPrice = BigDecimal.ZERO;
   protected Order() {}
   public Order(Set<OrderLine> orderLines) {
      this.orderLines = orderLines;
   }

   public Set<OrderLine> getOrderLines() {
      return Collections.unmodifiableSet(orderLines);
   }

   public void addOrderLine(OrderLine orderLine) {
      orderLines.add(orderLine);
//      orderLine.setOrder(this);
      totalPrice = orderLines.stream().map(OrderLine::price).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

}
