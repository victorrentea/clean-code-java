package victor.training.cleancode.java.fp.support;


import java.util.List;

public interface ProductRepo  {
  List<Long> getHiddenProductIds();
  List<Product> findAllById(List<Long> productIds);
}
