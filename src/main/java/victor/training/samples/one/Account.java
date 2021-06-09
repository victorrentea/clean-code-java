package victor.training.samples.one;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Account {
   private List<Product> products;
   private Map<String, List<Product>> dividedProducts;

   public List<Product> getProducts() {
      return products;
   }

   public void setProducts(List<Product> products) {
      this.products = products;
   }

   public void setDividedProducts(Map<String, List<Product>> dividedProducts) {

      this.dividedProducts = dividedProducts;
   }

   public Map<String, List<Product>> getDividedProducts() {
      return dividedProducts;
   }
}
