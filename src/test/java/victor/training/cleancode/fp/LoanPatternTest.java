package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderRepo;

import java.io.IOException;
import java.util.stream.Stream;

import static java.time.LocalDate.parse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanPatternTest {
   @Mock
   private OrderRepo orderRepo;
   @InjectMocks
   private FileExportService_Loan exporter;


   @Test
   public void exportOrders() throws IOException {
      Order order = new Order();
      order.setId(1L);
      order.setCreationDate(parse("2021-01-07"));
      when(orderRepo.findByActiveTrue()).thenReturn(Stream.of(order));

      new FileExportService(exporter).exportOrders();

      // NOW read the file from the disk ... Yuck!

//      assertEquals("OrderID;Date\n1;2021-01-07", sw.toString());
   }
}