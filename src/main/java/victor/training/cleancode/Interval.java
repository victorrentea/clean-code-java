package victor.training.cleancode;

import jakarta.persistence.Embeddable;

@Embeddable // doar doua campuri+coloane, incluse in @entity undeva
public record Interval(int start, int end) {
  public boolean intersectsWith(Interval other) {
    return start <= other.end && other.start <= end;
  }
}