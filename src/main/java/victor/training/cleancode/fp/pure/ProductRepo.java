package victor.training.cleancode.fp.pure;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepo extends JpaRepository<Product, Long> {

}
