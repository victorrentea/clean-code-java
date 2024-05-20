package victor.training.cleancode.fp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

class MutantPipelineTest {
  @Test
  @Disabled("until implemented")
  void totalOrderPrice() {
    int total = new MutantPipeline().totalActiveOrderPrice(List.of(
        new Order().setPrice(10).setActive(true),
        new Order().setPrice(5).setActive(true),
        new Order().setPrice(3).setActive(false)
    ));
    assertThat(total).isEqualTo(15);
  }


  @Test
  void getShipDates() {
    List<LocalDate> total = new MutantPipeline().getShipDates(List.of(
        new Order().setShipDate(now()).setActive(true),
        new Order().setShipDate(null).setActive(true),
        new Order().setShipDate(now()).setActive(false)
    ));
    assertThat(total).containsExactly(now());
  }

}