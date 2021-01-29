package trivia;

import trivia.questions.QuestionCategory;
import trivia.questions.Questions;

import java.util.ArrayList;
import java.util.List;

public class GameBetter implements IGame {
    public static final int BOARD_SIZE = 12;
    private static final int COINS_TO_WIN = 6;
    private static final int MINIMAL_PLAYERS = 2;

    private final List<Player> players;
    private final Questions questions;
    private int currentPlayerIndex;

    public GameBetter() {
        this.players = new ArrayList<>();
        this.questions = new Questions();
        this.currentPlayerIndex = 0;
    }

    @Override
    public boolean addPlayer(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    @Override
    public void rollDice(int roll) {
        Player currentPlayer = players.get(currentPlayerIndex);

        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                currentPlayer.setInPenaltyBox(false);
                System.out.println(currentPlayer + " is getting out of the penalty box");
                handlePlayerRoll(currentPlayer, roll);
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
            }
        } else {
            handlePlayerRoll(currentPlayer, roll);
        }
    }

    @Override
    public boolean handleCorrectAnswer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        if (currentPlayer.isInPenaltyBox()) {
            setNextPlayer();
            return true;
        } else {
            boolean winner = handleCorrectAnswer(currentPlayer);
            setNextPlayer();
            return winner;
        }
    }

    @Override
    public void handleWrongAnswer() {
        Player player = players.get(currentPlayerIndex);
        System.out.println("Question was incorrectly answered");
        System.out.println(player + " was sent to the penalty box");
        player.setInPenaltyBox(true);

        setNextPlayer();
    }

    private boolean handleCorrectAnswer(Player player) {
        System.out.println("Answer was correct!!!!");
        player.addCoin();
        int coins = player.getCoins();
        System.out.println(player + " now has " + coins + " Gold Coins.");

        return coins >= COINS_TO_WIN;
    }

    private void handlePlayerRoll(Player player, int roll) {
        player.advance(roll);

        System.out.println(player + "'s new location is " + player.getPlace());
        QuestionCategory questionCategory = currentCategory(player.getPlace());
        System.out.println("The category is " + questionCategory);
        String question = questions.pickQuestion(questionCategory)
            .orElseThrow(() -> new IllegalStateException("Out of questions"));
        System.out.println(question);
    }

    private boolean isPlayable() {
        return players.size() >= MINIMAL_PLAYERS;
    }

    private void setNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private static QuestionCategory currentCategory(int place) {
        return QuestionCategory.forIndex(place);
    }
}
