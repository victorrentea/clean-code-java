package victor.training.pure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {
   @InjectMocks
   private PriceService priceService;
   @Mock
   private LogisticsService logisticsService;
   @Mock
   private SupplierService supplierService;

   @Test
   public void computePrice() {
      Product product = new Product();
      priceService.computePrice(product);
   }
}