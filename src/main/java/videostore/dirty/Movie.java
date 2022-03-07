package videostore.dirty;

public class Movie {
   enum Category {
      CHILDREN,
      REGULAR,
      NEW_RELEASE,
      ELDERS;

   }

   private final String title;
   private final Category category;

   public Movie(String title, Category priceCode) {
      this.title = title;
      category = priceCode;
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }
}