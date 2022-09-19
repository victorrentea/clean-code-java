package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

    public static void main(String[] args) {
        // The big method is called from various foreign places in the codebase
        bigUglyMethod(new Task(5), 1);
        bigUglyMethod(new Task(4), 2);
        bigUglyMethod(new Task(3), 3);
        bigUglyMethod(new Task(2), 4);
        bigUglyMethod(new Task(1), 5);

        // TODO From my use-case #323, I call it too, to do more within:
        Task task = new Task(1);
        bigUglyMethod323(task, 2);

    }

    static void bigUglyMethod(Task task, int b) {
        bigStart(task, b);
        bigEnd(b);
    }

    static void bigUglyMethod323(Task task, int b) {
        bigStart(task, b);
        System.out.println("Logic just for CR323 : " + task);
        bigEnd(b);
    }

    private static void bigEnd(int b) {
        System.out.println("More Complex Logic " + b);
        System.out.println("More Complex Logic " + b);
        System.out.println("More Complex Logic " + b);
    }

    private static void bigStart(Task task, int b) {
        System.out.println("Complex Logic 1 " + task + " and " + b);
        System.out.println("Complex Logic 2 " + task);
        System.out.println("Complex Logic 3 " + task);
    }


    // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

    // Lord gave us tests!
    public void bossLevelFluff(List<Task> tasks, boolean cr323) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic3");
        List<Integer> taskIds = tasks.stream().map(Task::getId).collect(toList());

        for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();
        }

        if (cr323) { // TODO remove the boolean
            for (Task task : tasks) {
                System.out.println("My Logic: " + task);
            }
        }

        int index = 0;
        for (Task task : tasks) {
            index++;
            System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
        }
        System.out.println("Logic6 " + tasks.size());
        System.out.println("Task Ids: " + taskIds);
        System.out.println("Logic7");
    }

//    private void computePrice(List<Task> tasks) {
//        // late bugfix:
////        tasks.add(new Task(1)); if i ind you changing a param in a function named like this, your code is LYING . And for code review i
//        // will pay you a visit, witha friend
//    }

    public void bossLevelNoFluff(List<Task> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic7 " + tasks);
        System.out.println("Logic7");
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