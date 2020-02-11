package victor.training.cleancode;

import java.util.List;

@SuppressWarnings("all")
public class BouleanParameters {

	public static void main(String[] args) {
		main2(args);
	}
	public static void main2(String[] args) {
		main3(args);
	}
	public static void main3(String[] args) {
		main4(args);
	}
	public static void main4(String[] args) {
		// The method is called from various foreign places in the codebase
		bigUglyMethod(2, 1);
		bigUglyMethod(2, 1);
		bigUglyMethod(2, 1);
		bigUglyMethod(2, 1);
		bigUglyMethod(2, 1);

		// TODO From my use-case, I call it too, to do more within:
		bigUglyMethod323(2, 1);

	}

	static void bigUglyMethod(int b, int a) {
		beforeLogic();
		afterLogic();
	}
	static void bigUglyMethod323(int b, int a) {
		beforeLogic();
		System.out.println("motz"); // sa ruleze asta DOAR CAND EU CHEM FUNCTIA
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


	// ============== "BOSS" LEVEL: A lot harder to break down =================

	static void bossLevel(boolean stuff, boolean fluff, List<Integer> tasks) {
		int i = 0;
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
