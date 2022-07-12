package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

    public static void main(String[] args) {
//      new BooleanParameters().f();

    }

    private void f() {
        // The big method is called from various foreign places in the codebase
        bigUglyMethod(1, new Task(5));
        bigUglyMethod(2, new Task(4));
        bigUglyMethod(3, new Task(3));
        bigUglyMethod(4, new Task(2));
        bigUglyMethod(5, new Task(1));

        // TODO From my use-case #323, I call it too, to do more within:
        Task task = new Task(1);
        bigUglyMethod323(2, task);
    }

    public void bigUglyMethod323(int fas, Task task) {
        beforeStuff(fas, task);
        // daca bucata variabila este intr-un
        // - try { try/catch/finally, iei lambda ca vrei sa folosesti mecanismul de exceptii din Java.
        //-  for { incerci sa refactorezi. si uneori iei lambda
        // - if refactorezi incerci sa nu iei lambda
        // - daca esti pe baseline NICIODATA LAMBDA: ci tai functia
        maro(task);
        afterStuff(fas);
    }

    public void bigUglyMethod(int fas, Task task) {
        beforeStuff(fas, task);
        afterStuff(fas);
    }

    private void afterStuff(int fas) {
        System.out.println("More Complex Logic " + fas);
        System.out.println("More Complex Logic " + fas);
        System.out.println("More Complex Logic " + fas);
    }

    public void beforeStuff(int fas, Task task) {
        System.out.println("Complex Logic 1 " + task + " and " + fas);
        System.out.println("Complex Logic 2 " + task);
        System.out.println("Complex Logic 3 " + task);
    }

    public void maro(Task task) {
        System.out.println("cacaniu : " + task);
        System.out.println("cacaniu : " + task);
        System.out.println("cacaniu : " + task);
        System.out.println("cacaniu : " + task);
    }


    // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

    // Lord gave us tests!
    public void bossLevel(List<Task> tasks) {
        bossStart(tasks);
        bossEnd(tasks);
    }
    public void bossLevel323(List<Task> tasks) {
        bossStart(tasks);
        for (Task task : tasks) {
            System.out.println("My Logic: " + task);
        }
        bossEnd(tasks);
    }

    private void bossEnd(List<Task> tasks) {
        int index = 0;
        for (Task task : tasks) {
            index++;
            System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
        }
        System.out.println("Logic6 " + tasks.size());
        List<Integer> taskIds = tasks.stream()
                .filter(Objects::nonNull)
                .map(Task::getId)
                .collect(toList());
        System.out.println("Task Ids: " + taskIds);
        System.out.println("Logic7");
    }

    private void bossStart(List<Task> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic3");
        for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();
        }
    }

    public void bossLevelNoFluff(List<Task> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic7 " + tasks);
        System.out.println("Logic7");
    }

    private void inocenta(List<Task> tasks) {
        tasks.clear();
    }

}


class Task {
    private final int id;
    private boolean running;

    Task(int id) {
        this.id = id;
    }

    public void setRunning() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", running=" + running + '}';
    }
}