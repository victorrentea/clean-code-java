package videostore.dirty;

public class Movie {
   enum Category {
      CHILDRENS,
      REGULAR,
      NEW_RELEASE;
   }

   private final String title;
   private final Category category;

   // Bounded Context -----> "in universul Movie, CHILDRENS este category"
   // Acelasi CHILDREN in universul Pricing este priceCode.
   public Movie(String title, Category category) {
      if (category == null) {
         throw new IllegalArgumentException("null priceCode");
      }
      this.title = title;
      this.category = category;
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }
}