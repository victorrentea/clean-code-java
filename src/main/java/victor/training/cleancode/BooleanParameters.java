package victor.training.cleancode;

import java.util.List;

public class BooleanParameters {
	public static void main(String[] args) {
		// The big method is called from various foreign places in the codebase
		bigUglyMethod(1, 5);
		bigUglyMethod(2, 4);
		bigUglyMethod(3, 3);
		bigUglyMethod(4, 2);
		bigUglyMethod(5, 1);

		// TODO From my use-case #323, I call it too, to do more within:
		bigUglyMethod323(2, 1);

	}

	static void bigUglyMethod(int b, int a) {
		preLogic(b, a);
		afterLogic(b);
	}

	static void bigUglyMethod323(int b, int a) {
		preLogic(b, a);
		System.out.println("MY logic: " + a + " ,,," + b);
		afterLogic(b);
	}

	private static void afterLogic(int b) {
		System.out.println("More Complex Logic " + b);
		System.out.println("More Complex Logic " + b);
		System.out.println("More Complex Logic " + b);
	}

	private static void preLogic(int b, int a) {
		System.out.println("Complex Logic 1 " + a + " and " + b);
		System.out.println("Complex Logic 2 " + a);
		System.out.println("Complex Logic 3 " + a);
	}

	// ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to
	// break down =================

	public void bossLevelStuffFluffMyUC( List<Task> tasks) {
		int index = bossBefore(tasks);
		for (Task task : tasks) {
			// TODO When **I** call this method, I want this to run HERE, too:
			// System.out.println("My Logic: " + task);
			
		}
		bossAfter(tasks, index);
	}
	public void bossLevelStuffFluff( List<Task> tasks) {
		int index = bossBefore(tasks);
		bossAfter(tasks, index);
	}

	private void bossAfter(List<Task> tasks, int index) {
		for (Task task : tasks) {
			index++;
			System.out.println("Logic5 " + index + " on " + task.isRunning());
		}
		System.out.println("Logic6 " + tasks.size());
		System.out.println("Logic7");
	}

	private int bossBefore(List<Task> tasks) {
		System.out.println("Logic1");
		System.out.println("Logic2");
		System.out.println("Logic3");
		int index = 0; // TODO move closer to usages
		for (Task task : tasks) { 
			System.out.println("Logic4: Validate " + task);
			task.start();
		}
		return index;
	}
	
	
	
	
	public void bossLevelStuffNoFluff(boolean fluff, List<Task> tasks) {
		System.out.println("Logic1");
		System.out.println("Logic2");
		System.out.println("Logic7");
	}

	public void bossLevelNoStuff(boolean fluff, List<Task> tasks) {
		System.out.println("Logic1");
		System.out.println("Logic7");
	}

}

class Task {
	private boolean running;

	public void start() {
		running = true;
	}

	public boolean isRunning() {
		return running;
	}
}