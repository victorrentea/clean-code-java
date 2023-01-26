package videostore.horror;

// what could someone say about this code ?
public class Movie {
  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;

  enum MovieType {
    REGULAR(0),
    NEW_RELEASE(1),
    CHILDRENS(2);

    private final int code;

    MovieType(int code) {
      this.code = code;
    }
  }

  private String _title;
  private MovieType _priceCode;

  public Movie(String title, MovieType priceCode) {
    _title = title;
    _priceCode = priceCode;
  }

  public MovieType getPriceCode() {
    return _priceCode;
  }

  public void setPriceCode(MovieType arg) {
    _priceCode = arg;
  }

  public String getTitle() {
    return _title;
  }

  ;
}