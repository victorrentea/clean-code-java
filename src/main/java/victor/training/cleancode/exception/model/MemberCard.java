package victor.training.cleancode.exception.model;

import victor.training.cleancode.optional.Optional_Intro.Discount;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MemberCard {
   private final String barcode;
   private int fidelityPoints = 1;

   public MemberCard(String barcode) {
     this.barcode = Objects.requireNonNull(barcode);
   }
   public MemberCard(String barcode, int fidelityPoints) {
     this.barcode = Objects.requireNonNull(barcode);
      this.fidelityPoints = fidelityPoints;
   }

  public Optional<Discount> computeDiscount() {
    if (fidelityPoints >= 100) {
      return Optional.of(new Discount(5, Map.of()));
    }
    if (fidelityPoints >= 50) {
      return Optional.of(new Discount(3, Map.of()));
    }
//    return null; // NPE in the client
    // Null-Object Design Pattern. example: User.NULL_USER, User.ANONYMOUS
//    return new Discount(0, Map.of()); // the caller might not expect a Discount(0). that's not a discount.
//    return null; // this can get you fired. it's antisocial behavior.
    return Optional.empty();
  }

//  {
//    MemberCard m = new MemberCard("123");
//    m.getBarcode().toUpperCase();
//  }
//  @Nullable
  public String getBarcode() {
      return barcode;
   }

   public int getFidelityPoints() {
      return fidelityPoints;
   }

   public void setFidelityPoints(int fidelityPoints) {
      this.fidelityPoints = fidelityPoints;
   }
}
