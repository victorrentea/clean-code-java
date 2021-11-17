package victor.training.cleancode;

import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
public class IntervalEmbeddable {
   private int start;
   private int end;

   private IntervalEmbeddable() {} // WORK for hibernate!
   public IntervalEmbeddable(int start, int end) {
      this.start = start;
      this.end = end;
   }

   public boolean intersects(IntervalEmbeddable other) {
      return start <= other.end && other.start <= end;
   }

   public int getEnd() {
      return end;
   }

   public int getStart() {
      return start;
   }
}

