package victor.training.cleancode;

import javax.persistence.Embeddable;

//@Embeddable // TODO read pt Hibernate
public class Interval {
   // non final pt hibernate, dar effectively immutable ca n-ai setteri
   private int start;
   private int end;

   protected Interval() {} // doar pt Hibernate

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
