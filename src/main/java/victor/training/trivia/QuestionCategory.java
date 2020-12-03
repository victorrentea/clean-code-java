package victor.training.trivia;

public enum QuestionCategory {
   ROCK("Rock"),
   POP("Pop"),
   SPORTS("Sports"),
   SCIENCE("Science");

   private final String label;

   QuestionCategory(String label) {
      this.label = label;
   }

   public String getLabel() {
      return label;
   }
}
