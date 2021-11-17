package victor.training.pure.purity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product, Long> {
   @Query("SELECT p FROM Product p where p.name = ?1")
   Product findByName(String name);

}
