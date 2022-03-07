package victor.training.cleancode;

import javax.persistence.Embeddable;

@Embeddable
public class Interval {
   private  int start;
   private  int end;

   private Interval() {} // just for Hibernate

   public Interval(int start, int end) {
      if (start > end) { // GOOD
         throw new IllegalArgumentException("start larger than end");
      }
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval other) {
      // imagine 20 lines of logic 10 tests
      // imagine 20 lines of logic 10 tests
      // imagine 20 lines of logic 10 tests
      // imagine 20 lines of logic 10 tests
      // imagine 20 lines of logic 10 tests
      return start <= other.end && other.start <= end;
   }

   public int getStart() {
      return start;
   }

   public int getEnd() {
      return end;
   }
}
