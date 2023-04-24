package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public record Movie(String title, MovieCategory category) {
  public Movie {
    requireNonNull(title);
    requireNonNull(category);
  }
}