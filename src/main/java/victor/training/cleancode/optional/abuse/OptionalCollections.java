package victor.training.cleancode.optional.abuse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalCollections {
  // collections have a natural 'NULL' value
  public List<Coupon> findApplicableCoupons(Long customerId, List<Product> products) {
    System.out.println("Retrieve customer coupons " + customerId);
    Optional<Customer> customerFound = customerRepo.findById(customerId);
    if (customerFound.isEmpty()) {
      return Collections.emptyList();
    }
    List<Coupon> coupons = new ArrayList<>();
    System.out.println("Filter out not applicable coupons from " + coupons);
    return coupons;
  }

  public void caller(Long customerId) {
    List<Product> products = List.of(new Product(), new Product());
    List<Coupon> coupons = findApplicableCoupons(customerId, products);

    for (Coupon coupon : coupons) {
      System.out.println("Apply coupon: " + coupon);
    }
  }

  private interface CustomerRepo extends JpaRepository<Customer, Long> {
  }

  private static class Product {
  }

  private CustomerRepo customerRepo;

  private static class Coupon {
  }

  private static class Customer {
  }
}
