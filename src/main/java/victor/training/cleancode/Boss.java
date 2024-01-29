package victor.training.cleancode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
public class Boss {

  // Lord gave us tests! 👌 TODO run them
  public void bossLevel(boolean forScotland, List<Task> tasks, boolean cr323) {
    int index = 0;
    int taskSize = tasks.size();
    System.out.println("Logic1");
    List<Integer> taskIds = new ArrayList<>();
    if (forScotland) {
      System.out.println("Logic3");
      for (Task task : tasks) {
        System.out.println("Starting " + task);
        task.setStarted(true);

        taskIds.add(task.getId());

        if (cr323) { // TODO task = remove the boolean
          System.out.println("My Logic: " + task);
        }

        index++;
        System.out.println("Audit task #" + index + ": " + task);
      }
      System.out.println("Logic6 " + taskSize);
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

}
