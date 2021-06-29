package videostore.horror;

public class Movie {
   enum Category {
      CHILDREN,
      REGULAR,
      NEW_RELEASE
   }

   private String title;
   private Category category;

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

   ;
}