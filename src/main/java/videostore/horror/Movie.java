package videostore.horror;


// Daniel, dupa 2 min: "nu facem si noi Movie abstract" <- extinsa de ChildrenMovie, RegularMovie...
public class Movie {
   enum Category {
      REGULAR,
      NEW_RELEASE,
      CHILDRENS
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

   ;
}