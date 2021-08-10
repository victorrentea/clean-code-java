package victor.training.jpa.ddd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
public class OrderLine {
   @Id
   @GeneratedValue
   private Long id;

   @ManyToOne
   private Order order;

   @ManyToOne
   private Product product;

   private int itemCount;

   private BigDecimal itemPrice;
}
