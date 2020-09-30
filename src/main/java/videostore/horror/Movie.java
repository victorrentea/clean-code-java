package videostore.horror;

public class Movie {
   public enum Category {
      REGULAR,
      NEW_RELEASE,
      CHILDREN
   }

   private final String title;
   private final Category priceCode;

   public Movie(String title, Category priceCode) {
      this.title = title;
      this.priceCode = priceCode;
   }

   public Category getPriceCode() {
      return priceCode;
   }

   public String getTitle() {
      return title;
   }
}