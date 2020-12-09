package victor.training.cleancode;

public class Interval {
   private final int start;
   private final int end;

   public Interval(int start, int end) {
      if (start > end) {
         throw new IllegalArgumentException("start larger than end");
      }
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval interval1) {
      return start <= interval1.getEnd() && interval1.getStart() <= getEnd();
   }

   public int getStart() {
      return start;
   }

   public int getEnd() {
      return end;
   }
}
