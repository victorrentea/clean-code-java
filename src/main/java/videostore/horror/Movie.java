package videostore.horror;

import java.util.Objects;

public class Movie {


   public enum Type {
      REGULAR,
      NEW_RELEASE,
      CHILDREN,
      ELDERS;
   }
   private final String title;

   private final Type type;
   public Movie(String title, Type type) {
      this.title = Objects.requireNonNull(title);
      this.type = Objects.requireNonNull(type); // + ALTER TABLE SET NOT NULL + check prod DB
   }

   public Type getType() {
      return type;
   }

   public String getTitle() {
      return title;
   }

   public boolean isNewRelease() {
      return type == Type.NEW_RELEASE;
   }

}