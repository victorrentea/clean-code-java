package videostore.horror;


import java.util.Objects;

public class Movie {
   enum Type {
      CHILDRENS,
      REGULAR,
      NEW_RELEASE
   }

   private final String title;
   private final Type priceCode;

   public Movie(String title, Type priceCode) {
      this.title = title;
      this.priceCode = Objects.requireNonNull(priceCode);
   }

   public Type getPriceCode() {
      return priceCode;
   }

   public String getTitle() {
      return title;
   }
}