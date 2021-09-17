
package trivia;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;


public class GameTest {


	@Test
	public void caracterizationTest() {
		for (int seed = 1; seed < 10_000; seed++) {
			String expectedOutput = extractOutput(new Random(seed), new Game());
			String actualOutput = extractOutput(new Random(seed), new GameBetter());
			assertEquals(expectedOutput, actualOutput);
		}
	}

	private String extractOutput(Random rand, IGame aGame) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try (PrintStream inMemoryPrintStream = new PrintStream(byteArrayOutputStream)) {
			System.setOut(inMemoryPrintStream);
			
			
			aGame.addPlayer("Chet");
			aGame.addPlayer("Pat");
			aGame.addPlayer("Sue");
			
			boolean notWinner = false;
			do {
				aGame.roll(rand.nextInt(5) + 1);
				
				if (rand.nextInt(9) == 7) {
					notWinner = aGame.wrongAnswer();
				} else {
					notWinner = aGame.wasCorrectlyAnswered();
				}
				
			} while (notWinner);




		}
		return new String(byteArrayOutputStream
				.toByteArray());
	}
}
