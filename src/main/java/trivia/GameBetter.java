package trivia;

import trivia.question.category.factory.Factory;

import java.util.ArrayList;

public class GameBetter implements IGame {
    private ArrayList<Player> players = new ArrayList<>();
    private Factory questionCategoryFactory;
    private Player currentPlayer;
    private boolean isGettingOutOfPenaltyBox;

    private static final String UNPLAYABLE_GAME_MESSAGE = "Not enough players";
    private static final String INCORRECT_ANSWER_LOG = "Question was incorrectly answered";
    private static final String CORRECT_ANSWER_LOG  = "Answer was correct!!!!";
    private static final String SENT_TO_PENALTY_LOG  = " was sent to the penalty box";
    private static final String NOW_HAS_LOG  = " now has ";
    private static final String GOLD_COINS_LOG  = " Gold Coins.";
    private static final String NEW_LOCATION_IS_LOG  = "'s new location is ";
    private static final String THE_CATEGORY_IS_LOG  = "The category is ";
    private static final String GET_OUT_OF_PENALTY_LOG  = " is getting out of the penalty box";
    private static final String DOES_NOT_GET_OUT_OF_PENALTY_LOG = " is not getting out of the penalty box";
    private static final String IS_THE_CURRENT_PLAYER_LOG  = " is the current player";
    private static final String ROLLED_VALUE_LOG  = "They have rolled a ";
    private static final String WAS_ADDED_LOG  = " was added";
    private static final String PLAYER_NUMBER_LOG  = "They are player number ";


    public GameBetter() {
        this.questionCategoryFactory = new Factory();
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
            throw new RuntimeException(UNPLAYABLE_GAME_MESSAGE);
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
        System.out.println(INCORRECT_ANSWER_LOG);
        System.out.println(currentPlayer.getPlayerName() + SENT_TO_PENALTY_LOG);
    }

    private void logRewardDetails(){
        System.out.println(CORRECT_ANSWER_LOG );
        System.out.println(currentPlayer.getPlayerName()
                + NOW_HAS_LOG
                + currentPlayer.getPurse()
                + GOLD_COINS_LOG);
    }

    private void logPlayersPositionDetails() {
        System.out.println(currentPlayer.getPlayerName()
                + NEW_LOCATION_IS_LOG
                + currentPlayer.getPlace());

        System.out.println(THE_CATEGORY_IS_LOG + questionCategoryFactory
                .currentCategory(currentPlayer.getPlace())
                .getClass()
                .getSimpleName());
    }

    private void logPlayerGettingOutOfPenalty(boolean isRollUneven){
        if(isRollUneven){
            System.out.println(currentPlayer.getPlayerName() + GET_OUT_OF_PENALTY_LOG);
            return;
        }
        System.out.println(currentPlayer.getPlayerName() + DOES_NOT_GET_OUT_OF_PENALTY_LOG);
    }

    private void logCurrentPlayerAndHisRoll(int roll) {
        System.out.println(currentPlayer.getPlayerName() + IS_THE_CURRENT_PLAYER_LOG);
        System.out.println(ROLLED_VALUE_LOG + roll);
    }

    private void logAddedPlayersDetails(String playerName) {
        System.out.println(playerName + WAS_ADDED_LOG);
        System.out.println(PLAYER_NUMBER_LOG + players.size());
    }
}
