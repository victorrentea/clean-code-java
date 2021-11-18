
package victor.training.cleancode.assignment.trivia;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream inmemory = new PrintStream(baos)) {
			System.setOut(inmemory);
			
			
			aGame.add("Chet");
			aGame.add("Pat");
			aGame.add("Sue");
			
			boolean notOver = true;
			while(notOver) {
				aGame.roll(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					notOver = aGame.wrongAnswer();
				} else {
					notOver = aGame.wasCorrectlyAnswered();
				}

			}
		}
		String output = new String(baos.toByteArray());
		return output;
	}
}
