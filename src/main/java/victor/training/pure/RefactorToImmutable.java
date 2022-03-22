package victor.training.pure;

import lombok.Getter;
import victor.training.pure.Product.Category;

import java.math.BigDecimal;

class PriceService implements IPriceService {
   private final SupplierService supplierService;
   private final LogisticsService logisticsService;

   PriceService(SupplierService supplierService, LogisticsService logisticsService) {
      this.supplierService = supplierService;
      this.logisticsService = logisticsService;
   }

   @Override
   public void computePrice(Product product) {
      BigDecimal cost = supplierService.getCost(product.getSupplierId(), product.getId());
      BigDecimal deliveryCosts = logisticsService.estimateDeliveryCosts(product.getSupplierId());

      product.setPrice(cost.add(deliveryCosts));

      // @see PriceServiceTest
      final ProductForDiscountComputation product1 = new ProductForDiscountComputation(product);
      BigDecimal discountRate = applySupplierDiscount(product1, product1.getSupplierId());// -20%
      product.setPrice(product.getPrice().multiply(BigDecimal.ONE.subtract(discountRate)));

      applyDeliveryDiscount(product); // - 2 EUR
   }

   @Override
   public void sieu() {

   }

   private BigDecimal applySupplierDiscount(ProductForDiscountComputation product, Long supplierId) {
      System.out.println("200 lines of criminally complex logic using " + supplierId + " " + product.getId() + " and " + product.getCategory());
      return BigDecimal.valueOf(0.2);

   }

   private void applyDeliveryDiscount(Product product) {
      System.out.println("40 bugs and changes over the past year here" + product.getSupplierId() + " " + product.getId());
      BigDecimal discount = BigDecimal.valueOf(2);
      product.setPrice(product.getPrice().subtract(discount));
   }
}

@Getter
class ProductForDiscountComputation {
   private final Long id;
   private final Long supplierId;
   private final Category category;

   public ProductForDiscountComputation(Product product) {
      id= product.getId();
      supplierId = product.getSupplierId();
      category = product.getCategory();
   }
   ProductForDiscountComputation(Long id, Long supplierId, Category category) {
      this.id = id;
      this.supplierId = supplierId;
      this.category = category;
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