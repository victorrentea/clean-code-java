package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeServiceTest {

  @Test
  void greenTest() {
    new SomeService().greenMethod(1, new Task(1));
  }
}