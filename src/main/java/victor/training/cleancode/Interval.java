package victor.training.cleancode;

//@Embeddable
public class Interval {
   private final int start;
   private final int end;

   public Interval(int start, int end) {
      this.start = start;
      this.end = end;
      if (start > end) {
         throw new IllegalArgumentException("start larger than end");
      }
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }

}
