package victor.training.pure;

import victor.training.pure.Product.Category;

public class DiscountComputationInput {
   private final Long supplierId;
   private final Long productId;
   private final Category category;

   public DiscountComputationInput(Product product) {
      this.supplierId = product.getSupplierId();
            this.productId = product.getId();
            this.category = product.getCategory();
   }

   public Long getSupplierId() {
      return supplierId;
   }

   public Long getProductId() {
      return productId;
   }

   public Category getCategory() {
      return category;
   }
}
