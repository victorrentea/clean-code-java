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

   public static Category getCategoryForPlace(int place) {
      return Category.values()[place % 4];
//      switch (place % 4) {
//         case 0:
//            return POP;
//         case 1:
//            return SCIENCE;
//         case 2:
//            return SPORTS;
//         case 3:
//            return ROCK;
//
//         default:
//            throw new IllegalStateException("Unexpected value: " + place % 4);
//      }
   }
}
