package victor.training.cleancode;

public record Interval(int start, int end) {
  public Interval {
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }
}