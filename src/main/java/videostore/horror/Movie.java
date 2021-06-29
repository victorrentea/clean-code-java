package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
   enum Category {
      CHILDREN,
      NEW_RELEASE,
      REGULAR,
//      UNCATEGORIZED// Null object pattern
   }

   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
      this.title = title;
      this.category = requireNonNull(category); // + NOT NULL in DB
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }

}