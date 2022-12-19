package victor.training.cleancode.optional.abuse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalCollections {
  private static class Product {
//    List<String> ceva = Collections.emptyList();
  }
  private static class Coupon {}

  // REGULA NICIODATA IN CODUL TAU NU AI VOIE SA VEZI var/param de tip colectie lasate nulle!!!
  // reject PR
  public double calculatePrice(Product product, List<Coupon> coupons) {
    System.out.println("Compute price of " + product);
    if (!coupons.isEmpty()) {
      System.out.println("Apply coupons: " + coupons);
    }
    return 1; // imagine implem
  }

  public void caller() {
    // with coupons:
    calculatePrice(new Product(), List.of(new Coupon()));

    // without coupons:
    calculatePrice(new Product(), List.of());
  }
}
