package victor.training.pure;

import lombok.Getter;
import lombok.Value;
import victor.training.pure.Product.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

//@ApplicatonSerbie
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
      // -20%
      product.applyDiscountRate(getSupplierDiscountRate(new RecordForPriceComputation(product)));
      product.applyFixedDiscount(getDeliveryDiscount(product)); // - 2 EUR

//      productRepo.save(product);
   }

   /*private*/ BigDecimal getSupplierDiscountRate(RecordForPriceComputation product) {
      BigDecimal discountRate = BigDecimal.valueOf(0.2);
      System.out.println("80 lines of criminally complex logic using " +
                         product.getSupplierId() + " " + product.getId() +
                         " and " + product.getCategory());
      return discountRate;
   }

   private BigDecimal getDeliveryDiscount(Product product) {

      System.out.println("40 bugs and changes over the past year here" +
                         product.getSupplierId() + " " + product.getId());
      BigDecimal discount = BigDecimal.valueOf(2);
     return discount;
   }

@Value
static class RecordForPriceComputation {
   Long id;
   Long supplierId;
   Category category;
   public RecordForPriceComputation(Product product) {
      this.id= product.getId();
      this.supplierId = product.getSupplierId();
      this.category = product.getCategory();
   }
}
}

/// regarding unit testing: how do you do it for those private pure methods?


// standard, mutable object
class Product  {
   private Long id;
   private Long supplierId;
   private BigDecimal price;
   private Category category;

   @Getter
   private List<String> tags;

   public void applyDiscountRate(BigDecimal discountRate) {
      setPrice(getPrice().multiply(BigDecimal.ONE.subtract(discountRate)));
   }

   public void applyFixedDiscount(BigDecimal discount) {
      setPrice(getPrice().subtract(discount));
   }

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

//what do you think of tools like swagger codegen?
