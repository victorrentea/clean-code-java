package victor.training.refactoring;

public class Interval {
   private final int start;
   private final int end;

   public Interval(int start, int end) {
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval other) {
       // http://world.std.com/~swmcd/steven/tech/interval.html
       return getStart() <= other.getEnd() && other.getStart() <= getEnd();
   }

   public int getStart() {
      return start;
   }

   public int getEnd() {
      return end;
   }
}
