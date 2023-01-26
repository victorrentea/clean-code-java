package videostore.horror;

// what could someone say about this code ?
public class Movie {
  enum PriceCode {
    REGULAR,
    NEW_RELEASE,
    CHILDREN
  }

  private final String title;
  private final PriceCode priceCode;

  public Movie(String title, PriceCode priceCode) {
    this.title = title;
    this.priceCode = priceCode;
  }

  public PriceCode getPriceCode() {
    return priceCode;
  }

  public String getTitle() {
    return title;
  }
}