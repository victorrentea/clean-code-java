package videostore.horror;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

//class RegularMovie extends Movie{}
//class NewReleaseMovie extends Movie{}
//class ChildnemMovie extends Movie{ computePrice / getMaxDays}
//public abstract class Movie { absgract}
public class Movie {
  enum PriceCode {
    REGULAR(Rental::computeRegularPrice),
    NEW_RELEASE(daysRented -> (double) (daysRented * 3)),
    CHILDREN(daysRented -> {
      double price = 1.5;
      if (daysRented > 3)
        price += (daysRented - 3) * 1.5;
      return price;
    }),
    ELDERS(daysRented -> 1d);
    public final Function<Integer, Double> priceFormula;

    PriceCode(Function<Integer, Double> priceFormula) {
      this.priceFormula = priceFormula;
    }
  }

  private final String title;
  private final PriceCode priceCode;
  //  private final int ageRestriction;// atribute care au sens sa existe doar pentru anumit TIP de movie

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