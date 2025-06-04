package victor.training.cleancode.extra;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.cleancode.refactoring.SplitVariable.discount;

class SplitVariableTest {
  @Test
  void test() {
     assertThat(discount(1, 1)).isEqualTo(1);
     assertThat(discount(50, 1)).isEqualTo(50);
     assertThat(discount(51, 1)).isEqualTo(49);
     assertThat(discount(1, 101)).isEqualTo(0);
     assertThat(discount(50, 101)).isEqualTo(49);
     assertThat(discount(51, 101)).isEqualTo(48);
   }
}
