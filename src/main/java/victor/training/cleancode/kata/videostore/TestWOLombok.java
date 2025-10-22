package victor.training.cleancode.kata.videostore;

public class TestWOLombok {
  private String gg;
  private String ss;

  public TestWOLombok(String gg, String ss) { // unused
    this.gg = gg;
    this.ss = ss;
  }

  public String getGG() {// grayed out
    return this.gg;
  }

  public void setSS(String ss) { // grayed out
    this.ss = ss;
  }
}

