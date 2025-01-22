package victor.training.cleancode.java.fp.support;

public interface CustomerRepo {
   Customer findById(long customerId);
   int countByEmail(String email);

   Long save(Customer customer);
}
