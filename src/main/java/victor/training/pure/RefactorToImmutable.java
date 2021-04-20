package victor.training.pure;

import java.math.BigDecimal;

public class RefactorToImmutable {
   public static void main(String[] args) {
   }
}

class PriceService {
   private final SupplierService supplierService;
   private final LogisticsService logisticsService;

   PriceService(SupplierService supplierService, LogisticsService logisticsService) {
      this.supplierService = supplierService;
      this.logisticsService = logisticsService;
   }

   public void computePrice(Product product) {
      BigDecimal cost = supplierService.getCost(product.getSupplierId(), product.getId());
      BigDecimal deliveryCosts = logisticsService.estimateDeliveryCosts(product.getSupplierId());
      product.setPrice(cost.add(deliveryCosts));

      applySupplierDiscount(product);
      applyDeliveryDiscount(product);
   }

   private void applyDeliveryDiscount(Product product) {
      System.out.println("criminally complex logic (200 LOC) using " + product.getSupplierId() + " " + product.getId());
      BigDecimal discount = BigDecimal.ONE;
      product.setPrice(product.getPrice().subtract(discount));
   }

   private void applySupplierDiscount(Product product) {
      System.out.println("criminally complex logic (200 LOC) using " + product.getSupplierId() + " " + product.getId() + " and " + product.getCategory());
      BigDecimal discount = BigDecimal.valueOf(2);
      product.setPrice(product.getPrice().subtract(discount));
   }
}

class Product  {
   private Long id;
   private Long supplierId;
   private BigDecimal price;
   private Category category;

   public enum Category {
      HOME,
      ELECTRONICS
   }

   public Long getId() {
      return id;
   }

   public Long getSupplierId() {
      return supplierId;
   }

   public Category getCategory() {
      return category;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getPrice() {
      return price;
   }
}
interface SupplierService {
   BigDecimal getCost(Long supplierId, Long productId); // out of scope
}
interface LogisticsService {

   BigDecimal estimateDeliveryCosts(Long supplierId);
}