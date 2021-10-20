package victor.training.cleancode.support;

import lombok.ToString;

@ToString
public class Task {
   private final int id;
   private boolean running;
   private int detail = 0;

   public Task(int id) {
      this.id = id;
   }

   public void setDetail(int detail) {
      this.detail = detail;
   }

   public int getDetail() {
      return detail;
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
}
