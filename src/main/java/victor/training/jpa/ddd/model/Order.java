package victor.training.jpa.ddd.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {
   @Id
   @GeneratedValue
   private Long id;

   private LocalDateTime createDate;

   @OneToMany(mappedBy = "order")
   private List<OrderLine> orderLines = new ArrayList<>();

   private BigDecimal totalPrice;
}
