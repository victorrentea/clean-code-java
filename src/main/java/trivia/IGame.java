package trivia;

public interface IGame {
    /**
     * Adds a player to the game.
     *
     * @param playerName The player's name.
     * @return Whether adding the player was successful.
     */
    boolean addPlayer(String playerName);

    /**
     * Let the current player roll the dice.
     *
     * @param roll The number of the dice.
     */
    void rollDice(int roll);

    /**
     * Let the current player give a correct answer.
     *
     * @return {@code true} if the player has enough couns to win the game, {@code false} otherwise.
     */
    boolean handleCorrectAnswer();

    /** Let the current player give a wrong answer. */
    void handleWrongAnswer();
}
