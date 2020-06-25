package videostore.horror;

import java.util.Objects;

public class Movie {
   private final String title;
   private final PriceCode priceCode;

   public Movie(String title, PriceCode priceCode) {
      Objects.requireNonNull(priceCode);
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