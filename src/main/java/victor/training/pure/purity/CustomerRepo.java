package victor.training.pure.purity;

public interface CustomerRepo {
   Customer findById(long customerId);
   int countByEmail(String email);

   Long save(Customer customer);
}
