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


      BigDecimal supplierDiscount = applySupplierDiscount(new DiscountComputationInput(product));
      BigDecimal deliveryDiscount = applyDeliveryDiscount(new DiscountComputationInput(product));

      BigDecimal newPrice = product.getPrice()
          .subtract(supplierDiscount)
          .subtract(deliveryDiscount);
      product.setPrice(newPrice);
   }

   private BigDecimal applyDeliveryDiscount(DiscountComputationInput product) {
      System.out.println("criminally complex logic (200 LOC) using " + product.getSupplierId() + " " + product.getProductId());
      return BigDecimal.ONE;
   }

   private BigDecimal applySupplierDiscount(DiscountComputationInput input) {
      System.out.println("criminally complex logic (200 LOC) using " +
             input.getSupplierId() + " " + input.getProductId() + " and " + input.getCategory());
      return BigDecimal.valueOf(2);
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