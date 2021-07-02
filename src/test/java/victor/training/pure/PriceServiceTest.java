package victor.training.pure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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