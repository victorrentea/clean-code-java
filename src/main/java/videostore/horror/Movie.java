package videostore.horror;

// what could someone say about this code ?
public class Movie {
  enum MovieType {
    REGULAR,
    NEW_RELEASE,
    CHILDREN
  }

  private final String title;
  private final MovieType priceCode;

  public Movie(String title, MovieType priceCode) {
    this.title = title;
    this.priceCode = priceCode;
  }

  public MovieType getPriceCode() {
    return priceCode;
  }

  public String getTitle() {
    return title;
  }
}