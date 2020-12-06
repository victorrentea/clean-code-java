package victor.training.trivia;

public interface IGame {

	void addPlayer(String playerName);

	void roll(int roll);

	void correctAnswer();

	void wrongAnswer();

	boolean isGameOver();
}