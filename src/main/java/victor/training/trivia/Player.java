package victor.training.trivia;

public class Player {
   private final String name;
   private int place = 0;

   public Player(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void move(int roll) {
      // inglobat o mica regula de business in obiectzel.
      place += roll;
      if (place >= 12) {
         place -= 12;
      }
   }

   public int getPlace() {
      return place;
   }
}
