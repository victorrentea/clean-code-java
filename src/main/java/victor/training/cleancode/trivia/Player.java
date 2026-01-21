package victor.training.cleancode.trivia;

public class Player {
  private String name;
  private int place = 1;
  private int purse = 0;
  private boolean inPenaltyBox = false;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPlace() {
    return place;
  }

  public void setPlace(int place) {
    this.place = place;
  }

  public int getPurse() {
    return purse;
  }

  public void setPurse(int purse) {
    this.purse = purse;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }

  public void setInPenaltyBox(boolean inPenaltyBox) {
    this.inPenaltyBox = inPenaltyBox;
  }
}
