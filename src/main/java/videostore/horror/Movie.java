package videostore.horror;

import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public record Movie(String title, MovieType priceCode) {
  public Movie {
    requireNonNull(priceCode);
  }

  public boolean eligibleForBonus(int daysRented) {
    return priceCode == MovieType.NEW_RELEASE && daysRented >= 2;
  }
}