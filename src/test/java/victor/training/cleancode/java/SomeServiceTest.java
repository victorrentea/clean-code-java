package victor.training.cleancode.java;

import org.junit.jupiter.api.Test;

class SomeServiceTest {

  @Test
  void greenMethodTest() { // bad test (evergreen)
    new SomeService().greenMethod(1, new Task(5));
  }

  @Test
  void yellowMethodTest() {
    new SomeService().yellowMethod(1, new Task(5));
  }
}