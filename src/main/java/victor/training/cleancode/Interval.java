package victor.training.cleancode;

public record Interval(int start, int end) {
  public boolean intervalsIntersect(Interval other) {
    return start <= other.end && other.start <= end;
  }
}