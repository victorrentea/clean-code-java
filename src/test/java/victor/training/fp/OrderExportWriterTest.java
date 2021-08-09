package victor.training.fp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderExportWriterTest {
   @InjectMocks
   OrderExportWriter orderExportWriter;
   @Mock
   OrderRepo orderRepo;
   @Test
   public void test() {
      Mockito.when(orderRepo.findByActiveTrue()).thenReturn(Stream.of(new Order()));
      StringWriter writer = new StringWriter();
      orderExportWriter.writeOrders(writer);
      assertEquals("OrderID;Date\n" +
                   "null;null", writer.toString());
   }
}