package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.fp.support.Order;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ImperativeMindsetTest {
  @Test
  void totalOrderPrice() {
    int total = new ImperativeMindset().totalOrderPrice(List.of(
        new Order().setPrice(10).setActive(true),
        new Order().setPrice(5).setActive(true),
        new Order().setPrice(3).setActive(false)
    ));
    assertThat(total).isEqualTo(15);
  }


  @Test
  void getOrderPrices() {
    List<Integer> total = new ImperativeMindset().getOrderPrices(List.of(
        new Order().setPrice(10).setActive(true),
        new Order().setPrice(5).setActive(true),
        new Order().setPrice(3).setActive(false)
    ));
    assertThat(total).containsExactly(10, 5);
  }

}