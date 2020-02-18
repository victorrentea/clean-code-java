package victor.training.cleancode;

import java.util.List;

@SuppressWarnings("all")
public class BouleanParameters {

    public static void main(String[] args) {
        // The method is called from various foreign places in the codebase
        bigUglyMethod(2, 1);
        bigUglyMethod(2, 1);
        bigUglyMethod(2, 1);
        bigUglyMethod(2, 1);
        bigUglyMethod(2, 1);

        // TODO From my use-case, I call it too, to do more within:
        bigUglyMethodDacaAla(1, 2);
    }

    static void bigUglyMethod(int b, int a) {
        beforeLogic();
        afterLogic();
    }

    static void bigUglyMethodDacaAla(int a, int b) {
        beforeLogic();
        System.out.println("aici" + 1);
        afterLogic();
    }

    private static void beforeLogic() {
        System.out.println("Complex Logic");
        System.out.println("Complex Logic");
        System.out.println("Complex Logic");
    }

    private static void afterLogic() {
        System.out.println("More Complex Logic");
        System.out.println("More Complex Logic");
        System.out.println("More Complex Logic");
    }

    // ============== "BOSS" LEVEL: A lot harder to break down =================

    static void bossLevelStuffFluff(List<Integer> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic3");
        // TODO localize
        int i = 0;
        for (int task : tasks) {
            i++;
            System.out.println("Logic4 " + task);
            // TODO HERE, when call this method, I want MY own custom code to run here
            System.out.println("Logic5 " + i);
        }
        int j = 1;
        System.out.println("Logic6 " + j++);
        System.out.println("Logic7");
    }

    static void bossLevelStuffNoFluff(boolean fluff, List<Integer> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic7");
    }

    static void bossLevelNoStuff(boolean fluff, List<Integer> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic7");
    }
}
