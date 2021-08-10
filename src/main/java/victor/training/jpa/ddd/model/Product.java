package victor.training.jpa.ddd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
   @Id
   @GeneratedValue
   private Long id;

   private String name;

   @Lob
   private String description;

   private int shippingDaysEst;
   private int shippingCost;
   private boolean shippingToEasyBox;
   private String shippingProvider;
   private boolean shippingViaRegularPostOption;

   private boolean returnable;
   private int returnMaxDays;
   // TODO:
//   private List<ReturnCategory> returnCategories;

   @Enumerated(EnumType.STRING)
   private ProductCategory category;

   @ManyToOne
   private Supplier supplier;

}
