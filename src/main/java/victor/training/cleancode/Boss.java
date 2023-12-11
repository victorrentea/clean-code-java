package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

// "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
public class Boss {

  // Lord gave us tests! 👌 TODO run them
  public void bossLevel(List<Task> tasks, boolean cr323) {
//    int taskCount = tasks.size();
    System.out.println("Logic1");
//    check(tasks); // side effect on tasks
//    filteredTasks = filter(tasks); ca sa evite sa modifice lista
    // daca te prin in 2023, anul Domnului, ca modific COLECTII IN JAVA, te bat cu biciul [AI inserted]
    // nu e bine sa faci list.add list.remove, eviti map.put., daca poti sa
    //    creezi o noua colectie cu .stream() ... etc
    System.out.println("Logic3");
    int index = 0;
    List<Integer> taskIds = new ArrayList<>();
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
    System.out.println("Logic6 " + tasks.size());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }
  public void bossLevelNoFluff(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private void check(List<Task> tasks) {
    tasks.clear();// hehe!! 😈
  }

}
