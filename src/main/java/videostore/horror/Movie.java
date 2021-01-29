package videostore.horror;


import java.util.LinkedList;

public class Movie {
   private final String title;
   private final MovieCategory category;

   public Movie(String title, MovieCategory category) {
      this.title = title;
      this.category = category;

   }

   public MovieCategory getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }
}