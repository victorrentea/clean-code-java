package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import victor.training.cleancode.FunctionalChainsaw;
import victor.training.cleancode.fp.support.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static victor.training.cleancode.fp.support.ProductCategory.HOME;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FunctionalChainsawTest {

  @Mock
  private ProductRepo productRepo;

  @Mock
  private OrderRepo orderRepo;

  @InjectMocks
  private FunctionalChainsaw functionalChainsaw;

  private Product product = new Product("Chair", HOME)
      .setId(7L)
      .setDeleted(false);

  private Order anOrder() {
    return new Order()
        .setActive(true)
        .setCreationDate(LocalDate.now().minusDays(15))
        .setOrderLines(List.of(new OrderLine(product, 11)));
  }

  @Test
  void allInOneOrder() {
    when(orderRepo.findAll()).thenReturn(List.of(anOrder()));
    when(productRepo.getHiddenProductIds()).thenReturn(List.of());

    assertThat(functionalChainsaw.getHotProducts()).containsExactly(product);
  }

  @Test
  void inTwoOrders() {
    Order order1 = anOrder().setOrderLines(List.of(new OrderLine(product, 5)));
    Order order2 = anOrder().setOrderLines(List.of(new OrderLine(product, 6)));

    when(orderRepo.findAll()).thenReturn(List.of(order1, order2));
    when(productRepo.getHiddenProductIds()).thenReturn(List.of());

    assertThat(functionalChainsaw.getHotProducts()).containsExactly(product);
  }

  @Test
  void excludesDeletedProducts() {
    product.setDeleted(true);
    when(orderRepo.findAll()).thenReturn(List.of(anOrder()));
    when(productRepo.getHiddenProductIds()).thenReturn(List.of());

    assertThat(functionalChainsaw.getHotProducts()).isEmpty();
  }

  @Test
  void excludesHiddenProducts() {
    when(orderRepo.findAll()).thenReturn(List.of(anOrder()));
    when(productRepo.getHiddenProductIds()).thenReturn(List.of(product.getId()));

    assertThat(functionalChainsaw.getHotProducts()).isEmpty();
  }

  @Test
  void excludesOldOrders() {
    when(orderRepo.findAll()).thenReturn(List.of(anOrder().setCreationDate(LocalDate.now().minusYears(1))));
    when(productRepo.getHiddenProductIds()).thenReturn(List.of());

    assertThat(functionalChainsaw.getHotProducts()).isEmpty();
  }

  @Test
  void excludesInactiveOrders() {
    when(orderRepo.findAll()).thenReturn(List.of(anOrder().setActive(false)));
    when(productRepo.getHiddenProductIds()).thenReturn(List.of());

    assertThat(functionalChainsaw.getHotProducts()).isEmpty();
  }
}