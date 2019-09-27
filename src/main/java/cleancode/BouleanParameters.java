package cleancode;

import java.util.List;

@SuppressWarnings("ALL")
public class BouleanParameters {

    public static void main(String[] args) {
        // The method is called from various foreign places in the codebase
        bigUglyMethod(1,2,3,4,5);
        bigUglyMethod(1,2,3,4,5);
        bigUglyMethod(1,2,3,4,5);
        bigUglyMethod(1,2,3,4,5);
        bigUglyMethod(1,2,3,4,5);

        // TODO From my use-case, I call it too, to do more within:
        bigUglyMethodForGoldMemeber(1,2,3,4,5);

    }

    static void bigUglyMethod(int a, int b, int c, int d, int e) {
    	beforeLogic();
    	afterLogic();
    }
    static void bigUglyMethodForGoldMemeber(int a, int b, int c, int d, int e) {
    	beforeLogic();
    	System.out.println("Si asta");
        afterLogic();
    }

	private static void afterLogic() {
		System.out.println("more complex logic");
        System.out.println("more complex logic");
        System.out.println("more complex logic");
        System.out.println("more complex logic");
	}

	private static void beforeLogic() {
		System.out.println("complex logic");
    	System.out.println("complex logic");
        System.out.println("complex logic");
	}


    // ============== "BOSS" LEVEL: A log harder to break down =================

	static void bossLevelStuffFluff(List<Integer> trebi) {
		int i = 0;
		int j = 1;
		// more code
		// more code
		// more code
		for (int treaba:trebi) {
			i ++;
			// more code
			// more code
		}
		for (int treaba:trebi) {
			i ++;
			// TODO HERE, when call this method, I want MY own custom code to run here
		}
		// more code
		// more code
		
	}
	static void bossLevelStuff(List<Integer> trebi) {
		int i = 0;
		int j = 1;
		// more code
		// more code
		// more code
		
	}
    static void bossLevel(boolean fluff, List<Integer> trebi) {
        int i = 0;
        int j = 1;
        // more code

        // more code

    }
}
