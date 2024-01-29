package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
public class Boss {

  // Lord gave us tests! 👌 TODO run them
  public void bossLevel(boolean forScotland, List<Task> tasks, boolean cr323) {
    System.out.println("Logic1");
    if (forScotland) {
      System.out.println("Logic3");
      int index = 0;
      execute(tasks);

      // 1) have to iterate twice. So what? performance hit? NEVER in 18 years of exp have I seen this produce a perf inssue
      // with 1 exception NL stock trading company with response times < 50 microseconds 10^-6 0.00005 seconds
      //

      List<Integer> taskIds = tasks.stream().map(Task::getId).toList(); // accumulator = code smell since Java 8

//    execute(tasks);
      for (Task task : tasks) {
        System.out.println("Starting " + task);
        task.setStarted(true);


        if (cr323) { // TODO task = remove the boolean
          System.out.println("My Logic: " + task);
        }
        index++;
        System.out.println("Audit task #" + index + ": " + task);
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

  private void execute(List<Task> tasks) {
//    tasks.remove(0);// dirty Fri evening hack! < DON'T DO THIS!! you you still want a job.
    // DON'T MODIFY THE INPUT PARAMETER unless the method name explicitly says so
  }

}
