package victor.training.cleancode;

import java.util.List;

@SuppressWarnings("all")
public class BouleanParameters {

	public static void main(String[] args) {
		// The method is called from various foreign places in the codebase
		bigUglyMethod(2, 1, false);
		bigUglyMethod(2, 1, false);
		bigUglyMethod(2, 1, false);
		bigUglyMethod(2, 1, false);
		bigUglyMethod(2, 1, false);

		// TODO From my use-case, I call it too, to do more within:
		bigUglyMethod(2, 1, true);

	}

	static void bigUglyMethod(int b, int a, boolean crMeu) {
		System.out.println("Complex Logic " + a);
		System.out.println("Complex Logic " + a);
		System.out.println("Complex Logic " + a);

		if (crMeu) {
			System.out.println("#sieu");
		}

		System.out.println("More Complex Logic " + b);
		System.out.println("More Complex Logic " + b);
		System.out.println("More Complex Logic " + b);
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
