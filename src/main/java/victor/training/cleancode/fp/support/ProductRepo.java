package victor.training.cleancode.fp.support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
  List<Long> getHiddenProductIds();

}
