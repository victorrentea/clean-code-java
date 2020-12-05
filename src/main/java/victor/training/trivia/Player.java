package victor.training.trivia;

class Player {
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

  public int getPlace() {
    return place;
  }

  public void advance(int roll) {
    place += roll;
    if (place >= GameBetter.BOARD_SIZE) {
      place -= GameBetter.BOARD_SIZE;
    }
  }

  public void reward() {
    purse++;
  }

  public int getPurse() {
    return purse;
  }

  public void punish() {
    inPenaltyBox = true;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }

   public void free() {
    inPenaltyBox = false;
   }
}
