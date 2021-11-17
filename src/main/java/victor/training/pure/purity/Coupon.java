package victor.training.pure.purity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Coupon {
   private final ProductCategory category;
   private final int discountAmount;
   private boolean autoApply = true;

   public Coupon(ProductCategory category, int discountAmount) {
      this.category = category;
      this.discountAmount = discountAmount;
   }

   public boolean autoApply() {
      return autoApply;
   }

   public void setAutoApply(boolean autoApply) {
      this.autoApply = autoApply;
   }

   public boolean isApplicableFor(Product product) {
      return product.getCategory() == category;
   }

   public Double apply(Product product, Double price) {
      if (!isApplicableFor(product)) {
         throw new IllegalArgumentException();
      }
      return price - discountAmount;
   }
}
