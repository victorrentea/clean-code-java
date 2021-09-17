package trivia;

import trivia.question.category.factory.QuestionFactory;

import java.util.ArrayList;

public class GameBetter implements IGame {
    private ArrayList<Player> players = new ArrayList<>();
    private QuestionFactory questionCategoryFactory;
    private Player currentPlayer;
    private boolean isGettingOutOfPenaltyBox;


    public GameBetter() {
        this.questionCategoryFactory = new QuestionFactory();
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));

        logAddedPlayersDetails(playerName);
    }

    public void roll(int roll) {
        performRoundPrerequisites(roll);
        attemptToPlayRound(roll);
    }

    public boolean wasCorrectlyAnswered() {
        if (isGettingOutOfPenalty()) {
            moveToNextPlayer();
            return true;
        }
        rewardCorrectAnswer();
        return isWinner();
    }

    public boolean wrongAnswer() {
        logWrongAnswer();
        currentPlayer.setInPenaltyBox(true);

        moveToNextPlayer();
        return true;
    }

    private int howManyPlayers() {
        return players.size();
    }

    private void attemptToPlayRound(int roll) {
        if (isThePositionAllowingThePlayerToPlay(roll)) {
            playTurn(roll);
        }
    }

    private void performRoundPrerequisites(int roll) {
        checkIfTheGameIsPlayable();
        initializePlayerIfThisIsTheFirstRound();
        logCurrentPlayerAndHisRoll(roll);
    }

    private void checkIfTheGameIsPlayable() {
        if (!isPlayable()) {
            throw new RuntimeException("Not enough players");
        }
    }

    private boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    private void initializePlayerIfThisIsTheFirstRound() {
        if (this.currentPlayer == null) {
            this.currentPlayer = players.get(0);
        }
    }

    private boolean isThePositionAllowingThePlayerToPlay(int roll) {
        if (currentPlayer.isInPenaltyBox()) {
            return isPlayerGettingOutOfThePenaltyBox(roll);
        }
        return true;
    }

    private boolean isPlayerGettingOutOfThePenaltyBox(int roll) {
        isGettingOutOfPenaltyBox = isRollUneven(roll);
        logPlayerGettingOutOfPenalty(isGettingOutOfPenaltyBox);
        return isGettingOutOfPenaltyBox;
    }

    private boolean isRollUneven(int roll) {
        return roll % 2 != 0;
    }

    private void playTurn(int roll) {
        movePlayerToTheRightPlace(roll);
        ifTheLastPlaceWasReachedMoveToTheFirstPosition();
        logPlayersPositionDetails();

        askQuestion();
    }

    private void movePlayerToTheRightPlace(int roll) {
        currentPlayer.setPlace(currentPlayer.getPlace() + roll);
    }

    private void ifTheLastPlaceWasReachedMoveToTheFirstPosition() {
        if (currentPlayer.getPlace() > 11) {
            currentPlayer.setPlace(currentPlayer.getPlace() - 12);
        }
    }

    private void askQuestion() {
        this.questionCategoryFactory
                .currentCategory(currentPlayer.getPlace())
                .askQuestion();
    }

    private boolean isGettingOutOfPenalty(){
        if (currentPlayer.isInPenaltyBox()) {
            if (!isGettingOutOfPenaltyBox) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinner(){
        boolean winner = didPlayerWin();
        moveToNextPlayer();
        return winner;
    }

    private void rewardCorrectAnswer(){
        currentPlayer.setPurse(currentPlayer.getPurse() + 1);
        logRewardDetails();
    }

    private boolean didPlayerWin() {
        return !(currentPlayer.getPurse() == 6);
    }

    private void moveToNextPlayer() {
        if (isTheLastPlayer()) {
            currentPlayer = players.get(0);
            return;
        }
        currentPlayer = findNextPlayer();
    }

    private boolean isTheLastPlayer() {
        return players.lastIndexOf(currentPlayer) == players.size() - 1;
    }

    private Player findNextPlayer() {
        return players.get(players.lastIndexOf(currentPlayer) + 1);
    }


    //Logging can be seen as a violation of SRP, it can be moved to another class

    private void logWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getPlayerName() + " was sent to the penalty box");
    }

    private void logRewardDetails(){
        System.out.println("Answer was correct!!!!");
        System.out.println(currentPlayer.getPlayerName()
                           + " now has "
                           + currentPlayer.getPurse()
                           + " Gold Coins.");
    }

    private void logPlayersPositionDetails() {
        System.out.println(currentPlayer.getPlayerName()
                           + "'s new location is "
                           + currentPlayer.getPlace());

        System.out.println("The category is " + questionCategoryFactory
                .currentCategory(currentPlayer.getPlace())
                .getName());
    }

    private void logPlayerGettingOutOfPenalty(boolean isRollUneven){
        if(isRollUneven){
            System.out.println(currentPlayer.getPlayerName() + " is getting out of the penalty box");
            return;
        }
        System.out.println(currentPlayer.getPlayerName() + " is not getting out of the penalty box");
    }

    private void logCurrentPlayerAndHisRoll(int roll) {
        System.out.println(currentPlayer.getPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    private void logAddedPlayersDetails(String playerName) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }
}
