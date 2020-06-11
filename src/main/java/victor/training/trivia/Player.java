package victor.training.trivia;

public class Player {
   private final String name;
   private int place;
   private int purse;
   private boolean inPenaltyBox;

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

   public boolean isInPenaltyBox() {
      return inPenaltyBox;
   }

   public void addCoin() {
      purse ++;
   }

   public int getPurse() {
      return purse;
   }

   public void moveInPenaltyBox() {
      inPenaltyBox = true;
   }

   public void moveOutOfPenaltyBox() {
      inPenaltyBox = false;
   }

   public boolean isWinner() {
      return purse == 6;
   }
}
