package videostore.horror;

public class Movie {

  enum PriceCode {
    CHILDREN(2),
    REGULAR(0),
    NEW_RELEASE(1);

    private final int key;

    PriceCode(int key) {
      this.key = key;
    }
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

  ;
}