package victor.training.cleancode;

public class Interval {
  private final int start;
  private final int end;

  Interval(int start, int end) {
    if (start > end) { // constrained objects which are always valid
      throw new IllegalArgumentException("start larger than end");
    }
    this.start = start;
    this.end = end;
  }

  public boolean intersects(Interval other) {
    if (other == null) {
      throw new IllegalArgumentException("Null interval");
    }
    return start <= other.end && other.start <= end;
  }

  public int getEnd() {
    return end;
  }

  public int getStart() {
    return start;
  }
}
