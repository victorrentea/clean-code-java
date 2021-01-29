package trivia;

import static trivia.GameBetter.BOARD_SIZE;

public class Player {
    private final String name;

    private int place;
    private int coins;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void addCoin() {
        coins++;
    }

    public int getCoins() {
        return coins;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return name;
    }

    public void advance(int roll) {
        place = (place + roll) % BOARD_SIZE;
    }
}
