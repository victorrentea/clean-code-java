package videostore.horror;

import static videostore.horror.Movie.PriceCode.NEW_RELEASE;

public record MovieRental(Movie movie, int daysRented) {
  public int calculateRenterPoints() {
      int result = 1;
      boolean isNewRelease = movie().priceCode() == NEW_RELEASE;
      if (isNewRelease && daysRented() >= 2) {
          result ++ ;
      }
      return result;
  }

  public double calculateMoviePrice() {
        double result = 0.0;
        switch (movie().priceCode()) {
            case REGULAR:
                result += 2;
                if (daysRented() > 2)
                    result += (daysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                result += daysRented() * 3;
                break;
            case CHILDREN:
                result += 1.5;
                if (daysRented() > 3)
                    result += (daysRented() - 3) * 1.5;
                break;
        }
        return result;
    }
}