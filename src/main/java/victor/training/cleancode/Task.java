package victor.training.cleancode;

import lombok.Data;

public class Task {
  private final int id;
  private boolean started;

  public Task(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public Task setStarted(boolean started) {
    this.started = started;
    return this;
  }

  public boolean isStarted() {
    return started;
  }
  @Override
  public String toString() {
    return "Task(" +
           "id=" + id +
           ", started=" + started +
           ')';
  }
}