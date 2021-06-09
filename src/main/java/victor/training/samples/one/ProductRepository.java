package victor.training.samples.one;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
   Map<String, List<Product>> divideProducts(List<Product> products);
}
