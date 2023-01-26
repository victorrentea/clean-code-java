package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
//class RegularMovie extends Movie{}
//class NewReleaseMovie extends Movie{}
//class ChildnemMovie extends Movie{ computePrice / getMaxDays}
//public abstract class Movie { absgract}
public class Movie {
  enum PriceCode {
    REGULAR{
//      @Override
//      double computePrice(int daysRented) {
//        return 0;
//      }
    },
    NEW_RELEASE,
    CHILDREN,
    ELDERS,
    BURLACI;
//    abstract double computePrice(int daysRented);
    //    metode abstracte in ENUM frate !!
    // are sens doar pt bucati mici de tot de logica
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