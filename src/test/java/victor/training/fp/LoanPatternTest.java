package victor.training.fp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanPatternTest {
   @Mock
   private OrderRepo orderRepo;
   @Mock
   private UserRepo userRepo;
   @InjectMocks
   private OrderExportContentWriter contentWriter;

   @Test
   public void exportOrders() throws IOException {
      Order order = new Order().setId(1L).setCreationDate(LocalDate.parse("2021-01-07"));
      when(orderRepo.findByActiveTrue()).thenReturn(Stream.of(order));
      // WIP
      StringWriter sw = new StringWriter();
      contentWriter.writeContent(sw);

      assertEquals("OrderID;Date\n" +
                   "1;2021-01-07", sw.toString());
      // headache: load file from disk, check contentents
   }
}