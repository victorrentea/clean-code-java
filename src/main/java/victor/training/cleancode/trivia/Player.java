package victor.training.cleancode.trivia;

public class Player {
  // ❤️ sa explici codu
  // ❤️ ca daca-l schimbi, sa-l svhimb intr-un singur loc
//  private static final String SPACE = " "; // abuz
  private final String name;
  private int place = 1;
  private int coins;
  private boolean inPenaltyBox;

  public Player(String name) {
    this.name = name;
  }

  public void addCoin() {
    coins++;
  }

  public String getName() {
    return name;
  }

  public int getPlace() {
    return place;
  }

  public int getCoins() {
    return coins;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }

  public void setInPenaltyBox(boolean inPenaltyBox) {
    this.inPenaltyBox = inPenaltyBox;
  }

  public void movePlayer(int roll) {
    place += roll;
    while (place > Game.NUMBER_OF_TILES)
      place -= Game.NUMBER_OF_TILES;
  }
  //  Din punct de vedere OOP, vrei câmpuri private cu getter și setter?
  //
  //Dar, dacă vrei să faci OOP sustenabil, atunci încerci să
  // reduci din getter și setter și să adaugi și logică în
  // acele obiecte, să pui cărniță pe oasele câmpurilor.
}