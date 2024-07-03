package victor.training.cleancode;

public record Interval(int start, int end) {
  public boolean intersectsWith(Interval other) {
    return start <= other.end && other.start <= end;
  }
}