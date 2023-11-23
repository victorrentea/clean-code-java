package victor.training.cleancode;

public record Interval(int start, int end) {
  public Interval {
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }

  // ❤️❤️❤️
  public static Interval fromStrings(String s, String e) {
    int start = Integer.parseInt(s);
    int end = Integer.parseInt(e);
    return new Interval(start, end);
  }

  public Interval(String s, String e) {
    this(Integer.parseInt(s), Integer.parseInt(e));
  }
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }
}