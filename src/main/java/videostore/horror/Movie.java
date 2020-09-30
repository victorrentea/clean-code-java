package videostore.horror;

public class Movie {
   public enum Category {
      REGULAR,
      NEW_RELEASE,
      CHILDREN
   }

   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
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