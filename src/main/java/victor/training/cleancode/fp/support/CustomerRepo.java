package victor.training.cleancode.fp.support;

import java.util.Optional;

public interface CustomerRepo {
   Optional<Customer> findById(long customerId);
   int countByEmail(String email);

   Long save(Customer customer);
}
