package victor.training.pure;

import java.math.BigDecimal;

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

      // @see PriceServiceTest
      applySupplierDiscount(product); // -20%
      applyDeliveryDiscount(product); // - 2 EUR
   }

   private void applySupplierDiscount(Product product) {
      System.out.println("200 lines of criminally complex logic using " + product.getSupplierId() + " " + product.getId() + " and " + product.getCategory());
      BigDecimal discountRate = BigDecimal.valueOf(0.2);
      product.setPrice(product.getPrice().multiply(BigDecimal.ONE.subtract(discountRate)));
   }

   private void applyDeliveryDiscount(Product product) {
      System.out.println("40 bugs and changes over the past year here" + product.getSupplierId() + " " + product.getId());
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