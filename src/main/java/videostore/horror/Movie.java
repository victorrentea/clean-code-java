package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
  enum PriceCode {
    REGULAR,
    NEW_RELEASE,
    CHILDREN
  }

  private final String title;
  private final PriceCode priceCode;

  public Movie(String title, PriceCode priceCode) {
    this.title = requireNonNull(title);
    this.priceCode = requireNonNull(priceCode);
  }

  public PriceCode getPriceCode() {
    return priceCode;
  }

  public String getTitle() {
    return title;
  }
}