package victor.training.trivia;

public enum Category {
   POP("Pop"),
   SCIENCE("Science"),
   SPORTS("Sports"),
   ROCK("Rock");

   public final String label;
   Category(String label) {
      this.label = label;
   }
}
