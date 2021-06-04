package videostore.horror;

import victor.training.cleancode.pretend.Autowired;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static videostore.horror.Movie.Category.NEW_RELEASE;

//@Configurable
public class Movie {
   public boolean isNewRelease() {
      return getCategory() == NEW_RELEASE;
   }

   public enum Category {
      CHILDREN,
      REGULAR,
      NEW_RELEASE;
   }

//   @Autowired
//   private javax.validator.Validator validator;
//   @Size(min =2)
   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
      this.title = requireNonNull(title);
      this.category = requireNonNull(category); // + NOT NULL
//      validator.validate(this);
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