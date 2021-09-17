package trivia;

public class Player {
    private String playerName;
    private int place;
    private int purse;
    private boolean isInPenaltyBox;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
        return isInPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;
    }
}
