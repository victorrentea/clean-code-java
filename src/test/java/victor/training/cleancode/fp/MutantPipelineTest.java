package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.MutantPipeline;
import victor.training.cleancode.support.*;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MutantPipelineTest {


  public static final long SSO_ID = 1L;
  public static final long PAYMENT_CARD_ID = 123L;
  PaymentCardRepository repository = mock(PaymentCardRepository.class);
  MutantPipeline mutantPipeline = new MutantPipeline(repository, new PaymentCardMapper());

  @Test
  void totalOrderPrice() {
    var orders = List.of(
        new Order().setPrice(10).setActive(true),
        new Order().setPrice(5).setActive(true),
        new Order().setPrice(3).setActive(false)
    );
    int result = mutantPipeline.totalActiveOrderPrice(orders);
    assertThat(result).isEqualTo(15);
  }

  @Test
  void getShipDates() {
    var orders = List.of(
        new Order().setShipDate(now()).setActive(true),
        new Order().setShipDate(null).setActive(true),
        new Order().setShipDate(now()).setActive(false)
    );
    var result = mutantPipeline.getShipDates(orders);
    assertThat(result).containsExactly(now());
  }

  @Test
  void updateCardAlias_success() {
    when(repository.findById(PAYMENT_CARD_ID)).thenReturn(of(new PaymentCard().setId(SSO_ID)));
    when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

    var result = mutantPipeline.updateCardAlias(PAYMENT_CARD_ID, SSO_ID, "UpdatedAlias");

    assertThat(result).isEqualTo(new PaymentCardDto(SSO_ID, "UpdatedAlias"));
  }

  @Test
  void updateCardAlias_notFound_throwsException() {
    when(repository.findById(PAYMENT_CARD_ID)).thenReturn(empty());

    assertThatThrownBy(() -> mutantPipeline.updateCardAlias(PAYMENT_CARD_ID, SSO_ID, "UpdatedAlias"))
        .isInstanceOf(IllegalArgumentException.class);
  }
}