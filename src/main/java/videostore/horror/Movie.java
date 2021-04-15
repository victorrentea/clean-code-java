package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
   public boolean isNewRelease() {
      return getGenre() == Genre.NEW_RELEASE;
   }

   public enum Genre {
      CHILDREN,
      REGULAR,
      NEW_RELEASE
   }
   private final String title;
   private final Genre genre;

   public Movie(String title, Genre genre) {
      this.title = title;
      this.genre = requireNonNull(genre);
   }

   public Genre getGenre() {
      return genre;
   }

   public String getTitle() {
      return title;
   }

   ;
}