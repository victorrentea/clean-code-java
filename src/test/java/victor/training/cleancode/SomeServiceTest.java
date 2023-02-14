package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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