package victor.training.cleancode;

public record Range(int start, int end) {
  public Range {
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }
  public boolean intersects(Range other) {
    return start <= other.end && other.start <= end;
  }
}