package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category {
		REGULAR,
		CHILDRENS,
		NEW_RELEASE
	}
   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
      this.title = title;
      this.category = requireNonNull(category);
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Movie movie = (Movie) o;
      return Objects.equals(title, movie.title) && category == movie.category;
   }

   @Override
   public int hashCode() {
      return Objects.hash(title, category);
   }
}