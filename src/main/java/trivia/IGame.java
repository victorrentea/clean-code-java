package trivia;

public interface IGame {

	void addPlayer(String playerName);

	void roll(int roll);

	boolean wasCorrectlyAnswered();

	boolean wrongAnswer();

}