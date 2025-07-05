package victor.training.cleancode.support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
  List<Long> getHiddenProductIds();
  List<Product> findAllById(List<Long> productIds);

}
