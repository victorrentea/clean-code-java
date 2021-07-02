package victor.training.cleancode;

import javax.persistence.Embeddable;

@Embeddable
public class Interval {
   private int start;
   private int end;
   private Interval() {} // doar pt hibernate.

   public Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }

   public int getStart() {
      return start;
   }

   public int getEnd() {
      return end;
   }
}
