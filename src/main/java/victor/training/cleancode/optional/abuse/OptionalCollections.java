package victor.training.cleancode.optional.abuse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalCollections {
  private static class Product {}
  private static class Coupon {}

  public double calculatePrice(Product product, Optional<List<Coupon>> coupons) {
    System.out.println("Compute price of " + product);
    if (coupons.isPresent()) {
      System.out.println("Apply coupons: " + coupons.get());
    }
    return 1; // imagine implem
  }

  public void caller() {
    // with coupons:
    calculatePrice(new Product(), Optional.of(Arrays.asList(new Coupon())));

    // without coupons:
    calculatePrice(new Product(), Optional.empty());
  }
}
