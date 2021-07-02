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

import static java.time.LocalDate.parse;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanPatternTest {
   @Mock
   private OrderRepo orderRepo;
   @InjectMocks
   private ExportService contentWriter;


   @Test
   public void exportOrders() throws IOException {
      Order order = new Order();
      order.setId(1L);
      order.setCreationDate(parse("2021-01-07"));
      when(orderRepo.findByActiveTrue()).thenReturn(Stream.of(order));

      contentWriter.exportOrders();

      // NOW read the file from the disk ... Yuck!

//      assertEquals("OrderID;Date\n1;2021-01-07", sw.toString());
   }
}