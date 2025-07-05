package victor.training.cleancode.support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {
  Optional<Supplier> findByName(String name);

  Optional<Supplier> findByCode(String code);
  boolean existsByName(String name);
}
