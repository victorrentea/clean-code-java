package victor.training.pure;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

//public class RefactorToImmutable {
//   public static void main(String[] args) {
//      new PriceService()
//   }
//}

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

      BigDecimal rate = getDeliveryDiscountRate(product);

      BigDecimal supplierDiscount = getSupplierDiscount(product);
      BigDecimal price = altaPura(cost, deliveryCosts, rate, supplierDiscount);

      product.setPrice(price);

   }

   private BigDecimal altaPura(BigDecimal cost, BigDecimal deliveryCosts, BigDecimal rate, BigDecimal supplierDiscount) {
      BigDecimal price = cost.add(deliveryCosts)
          .multiply(rate)
          .subtract(supplierDiscount);
      return price;
   }

   @Value
   class Money {
      BigDecimal amount;
      Currency currency;
   }

   /** -10%
    * @return*/
   private BigDecimal getDeliveryDiscountRate(Product product) {
      System.out.println("criminally complex logic (200 LOC) using " + product.getSupplierId() + " " + product.getId());
      return BigDecimal.valueOf(0.9);
   }

   /** -2 EUR */
   private BigDecimal getSupplierDiscount(Product product) {
      System.out.println("criminally complex logic (200 LOC) using " + product.getSupplierId() + " " + product.getId() + " and " + product.getCategory());
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