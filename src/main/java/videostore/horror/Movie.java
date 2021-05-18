package videostore.horror;

public class Movie {
   public enum Type {
      REGULAR,
      NEW_RELEASE,
      CHILDREN
   }

   private final String title;
   private final Type priceCode;

   public Movie(String title, Type priceCode) {
      this.title = title;
      this.priceCode = priceCode;
   }

   public Type getPriceCode() {
      return priceCode;
   }

   public String getTitle() {
      return title;
   }

}