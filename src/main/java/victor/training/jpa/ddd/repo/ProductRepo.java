package victor.training.jpa.ddd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.jpa.ddd.model.Product;

import javax.persistence.Entity;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
