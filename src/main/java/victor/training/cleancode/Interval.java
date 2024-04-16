package victor.training.cleancode;

public class Interval {
  private final int start;
  private final int end;

  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public boolean intervalsIntersect(Interval interval2) {
    if (this == null || interval2 == null) {
      throw new IllegalArgumentException("Null interval");
    }
    return start <= interval2.end
           && interval2.start <= end;
  }

  public int getEnd() {
    return end;
  }

  public int getStart() {
    return start;
  }
}
