package videostore.horror;


import java.util.Objects;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

public class Movie {
  enum PriceCode {
    CHILDREN,
    REGULAR,
    //    ELDERS,
    NEW_RELEASE;
    //BOO!:  https://dzone.com/articles/functional-programming-patterns-with-java-8
    //public final BiFunction<SomeService, Integer:daysRented, Integer>

    //public abstract void computePrice(int daysRented);
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