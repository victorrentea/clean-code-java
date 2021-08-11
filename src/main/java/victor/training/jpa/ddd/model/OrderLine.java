package victor.training.jpa.ddd.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
public class OrderLine {
   @Id
   @GeneratedValue
   private Long id;

//   @ManyToOne
//   private Order order;

//   @ManyToOne
//   Product product;
   private Long productId;// you should manually create the FK

   private int itemCount;

   private BigDecimal itemPrice;

   protected OrderLine() {}
   public OrderLine(Long productId, int itemCount, BigDecimal itemPrice) {
      this.productId = Objects.requireNonNull(productId);
      if (itemCount <= 0) {
         throw new IllegalArgumentException();
      }
      this.itemCount = itemCount;
      this.itemPrice = Objects.requireNonNull(itemPrice);
   }

   public BigDecimal price() {
      return itemPrice.multiply(BigDecimal.valueOf(itemCount));
   }
}
