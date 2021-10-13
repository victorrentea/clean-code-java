package victor.training.cleancode;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuardClauses {
   public int getPayAmount(Marine marine) {
      if (marine == null) {
         throw new RuntimeException("Marine is null");
      }
      if (isDead(marine)) {
         return deadAmount();
      } // network call
      if (marine.isRetired()) {
         return retiredAmount();
      }
      if (marine.getYearsService() == null) {
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }

      int result = computeRegularPayAmount(new MarinePayComputationDetails(marine));

      // HEAVY logic here...
      return result;
   }

   private int computeRegularPayAmount(MarinePayComputationDetails marine) {
      int result = marine.getYearsOfService() * 100;
      if (marine.getAwards() > 0) {
         result += 1000;
      }
      if (marine.getAwards() >= 3) {
         result += 2000;
      }
      return result;
   }


   public void method(List<Integer> list) {
      // single point of return
      if (list == null) {
         return;
      }
      for (Integer integer : list) {
         
      }
   }

   private boolean isDead(Marine marine) {
      // after 500 millis
      return false;
   }

   private int deadAmount() {
      return 1;
   }

   private int retiredAmount() {
      return 2;
   }

}


//@Data // hate   hashCOde + setter = panica.
@Value // love
class MarinePayComputationDetails {
   /*private final*/ int awards;
   /*private final*/ int yearsOfService;

   public MarinePayComputationDetails(int awards, int yearsOfService) {
      this.awards = awards;
      this.yearsOfService = yearsOfService;
   }
   public MarinePayComputationDetails(Marine marine) {
      this(marine.getAwards().size(), marine.getYearsService());
   }
//
//   MarinePayComputationDetails(int awards, int yearsOfService) {
//      this.awards = awards;
//      this.yearsOfService = yearsOfService;
//   }
//
//   public int getAwards() {
//      return awards;
//   }
//
//   public int getYearsOfService() {
//      return yearsOfService;
//   }
//
//   @Override
//   public String toString() {
//      return "MarinePayComputationDetails{" +
//             "awards=" + awards +
//             ", yearsOfService=" + yearsOfService +
//             '}';
//   }
//
//   @Override
//   public boolean equals(Object o) {
//      if (this == o) return true;
//      if (o == null || getClass() != o.getClass()) return false;
//      MarinePayComputationDetails that = (MarinePayComputationDetails) o;
//      return awards == that.awards && yearsOfService == that.yearsOfService;
//   }
//
//   @Override
//   public int hashCode() {
//      return Objects.hash(awards, yearsOfService);
//   }
}

//record A(int awards, int yearsOfService) {
//}

@Data
class Marine {
   private boolean dead;
   private boolean retired;
   private Integer yearsService;
   private List<Award> awards = new ArrayList<>();

   Marine(boolean dead, boolean retired, Integer yearsService) {
      this.dead = dead;
      this.retired = retired;
      this.yearsService = yearsService;
   }

   public List<Award> getAwards() {
      return awards;
   }

   public Integer getYearsService() {
      return yearsService;
   }

   public boolean isRetired() {
      return retired;
   }

   public boolean isDead() {
      return dead;
   }
}

class Award {

}