package victor.training.cleancode.immutable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
   @Mock
   private SupplierService supplierService;
   @Mock
   private LogisticsService logisticsService;
   @InjectMocks
   private PriceService priceService;

   @Test
   public void computePrice() {
      when(supplierService.getCost(any(), any())).thenReturn(valueOf(80));
      when(logisticsService.estimateDeliveryCosts(any())).thenReturn(valueOf(20));

      Product product = new Product();
      priceService.computePrice(product);

      assertThat(product.getPrice()).isEqualByComparingTo(valueOf(78));

   }
}