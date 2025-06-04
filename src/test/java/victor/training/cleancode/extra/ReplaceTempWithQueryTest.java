package victor.training.cleancode.extra;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.refactoring.ReplaceTempWithQuery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ReplaceTempWithQueryTest {
  @Test
  void basePrice() {
        ReplaceTempWithQuery pc = new ReplaceTempWithQuery(1, 5);
      assertThat(pc.computePrice()).isCloseTo(4.9, within(0.001));
      assertThat(pc.computeFidelityPoints()).isEqualTo(2);
    }

  @Test
  void quantityDiscount() {
        ReplaceTempWithQuery pc = new ReplaceTempWithQuery(10, 5);
      assertThat(pc.computePrice()).isCloseTo(49, within(0.001));
      assertThat(pc.computeFidelityPoints()).isEqualTo(25);
    }

  @Test
  void amountDiscount() {
        ReplaceTempWithQuery pc = new ReplaceTempWithQuery(1, 1001);
      assertThat(pc.computePrice()).isCloseTo(950.95, within(0.01));
      assertThat(pc.computeFidelityPoints()).isEqualTo(500);
    }
}