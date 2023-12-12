package victor.training.cleancode;

import java.util.List;

// "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
public class  Boss {

  // Lord gave us tests! 👌 TODO run them
  public void bossLevel(List<Task> tasks) {
    int index = bossStart(tasks);
    bossEnd(tasks, index);
  }
  public void bossLevel323(List<Task> tasks) {
    int index = bossStart(tasks);
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    bossEnd(tasks, index);
  }

  private void bossEnd(List<Task> tasks, int index) {
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
    System.out.println("Logic6 " + tasks.size());
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private int bossStart(List<Task> tasks) {
    //    int taskCount = tasks.size();
    System.out.println("Logic1");
//    check(tasks); // side effect on tasks
//    filteredTasks = filter(tasks); ca sa evite sa modifice lista
    // daca te prin in 2023, anul Domnului, ca modific COLECTII IN JAVA, te bat cu biciul [AI inserted]
    // nu e bine sa faci list.add list.remove, eviti map.put., daca poti sa
    //    creezi o noua colectie cu .stream() ... etc
    System.out.println("Logic3");
    int index = 0;
    // Split Loop Refactoring: concerns:
    // 1) performance
    // 2) bugs
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }

    //    List<Integer> taskIds = new ArrayList<>();
//    for (Task task : tasks) {
//      taskIds.add(task.getId());
//    }

//    tasks.forEach(task -> taskIds.add(task.getId())); // nu e FP pt ca cauzeaza side effect

//    List<Integer> taskId = tasks.stream().map(Task::getId).toList(); // ESTE FP pt ca doar calculeaza si intoarce
    return index;
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
