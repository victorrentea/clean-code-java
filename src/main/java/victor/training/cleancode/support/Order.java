package victor.training.cleancode.support;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Order {
   private Long id;
   private LocalDate creationDate;
   private boolean active;
   private long totalPrice;
}