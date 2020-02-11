package victor.training.cleancode;

import java.util.List;

@SuppressWarnings("all")
public class BouleanParameters {

	public static void main(String[] args) {
		// The method is called from various foreign places in the codebase
		bigUglyMethod(1, 2);
		bigUglyMethod(1, 2);
		bigUglyMethod(1, 2);
		bigUglyMethod(1, 2);
		bigUglyMethod(1, 2);

		// TODO From my use-case, I call it too, to do more within:
		bigUglyMethod(1, 2);

	}

	static void bigUglyMethod(int a, int b) {
		System.out.println("Complex Logic");
		System.out.println("Complex Logic");
		System.out.println("Complex Logic");

		System.out.println("More Complex Logic");
		System.out.println("More Complex Logic");
		System.out.println("More Complex Logic");
	}

	// ============== "BOSS" LEVEL: A lot harder to break down =================

	static void bossLevel(boolean stuff, boolean fluff, List<Integer> tasks) {
		int i = 0; // TODO localize
		int j = 1;
		System.out.println("Logic1");
		if (stuff) {
			System.out.println("Logic2");
			if (fluff) {
				System.out.println("Logic3");
				for (int task : tasks) {
					i++;
					System.out.println("Logic4 " + task);
					// TODO HERE, when call this method, I want MY own custom code to run here
					System.out.println("Logic5 " + i);
				}
				System.out.println("Logic6 " + j++);
			}
		}
		System.out.println("Logic7");
	}
}
