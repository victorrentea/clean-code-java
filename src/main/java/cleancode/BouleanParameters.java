package cleancode;

import java.util.List;

@SuppressWarnings("all")
public class BouleanParameters {

	public static void main(String[] args) {
		// The method is called from various foreign places in the codebase
		bigUglyMethodStandard(1, 2, 3, 4, 5);
		bigUglyMethodStandard(1, 2, 3, 4, 5);
		bigUglyMethodStandard(1, 2, 3, 4, 5);
		bigUglyMethodStandard(1, 2, 3, 4, 5);
		bigUglyMethodStandard(1, 2, 3, 4, 5);

		// TODO From my use-case, I call it too, to do more within:
		bigUglyMethod323(1, 2, 3, 4, 5);

	}

	static void bigUglyMethodStandard(int a, int b, int c, int d, int e) {
		beforeLogic();
		afterLogic();
	}
	static void bigUglyMethod323(int a, int b, int c, int d, int e) {
		beforeLogic();
		// ceva in plus
		afterLogic();
	}

	private static void afterLogic() {
		System.out.println("More Complex Logic");
		System.out.println("More Complex Logic");
		System.out.println("More Complex Logic");
	}

	private static void beforeLogic() {
		System.out.println("Complex Logic");
		System.out.println("Complex Logic");
		System.out.println("Complex Logic");
	}

	// ============== "BOSS" LEVEL: A log harder to break down =================

	static void bossLevelStuffStuffStandard(List<Integer> tasks) {
		int i = 0;
		int j = 1;
		System.out.println("Logic1");
		System.out.println("Logic2");
		System.out.println("Logic3");
		for (int task : tasks) {
			i++;
			System.out.println("Logic4 " + task);
			System.out.println("Logic5 " + i);
		}
		System.out.println("Logic6 " + j++);
		System.out.println("Logic7");
	}
	static void bossLevelStuffStuffAMea(List<Integer> tasks) {
		int i = 0;
		int j = 1;
		System.out.println("Logic1");
		System.out.println("Logic2");
		System.out.println("Logic3");
		for (int task : tasks) {
			i++;
			System.out.println("Logic4 " + task);
			System.out.println("hen call this method, I want MY own custom code to run here");
			System.out.println("Logic5 " + i);
		}
		System.out.println("Logic6 " + j++);
		System.out.println("Logic7");
	}

	static void bossLevelStuffNoFluff(boolean fluff, List<Integer> tasks) {
		int i = 0;
		int j = 1;
		System.out.println("Logic1");
		System.out.println("Logic2");
		System.out.println("Logic7");
	}




	static void bossLevelNoStuff(boolean fluff, List<Integer> tasks) {
		int i = 0;
		int j = 1;
		System.out.println("Logic1");

		System.out.println("Logic7");
	}
}
