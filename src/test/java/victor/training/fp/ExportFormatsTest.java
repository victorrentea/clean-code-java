package victor.training.fp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExportFormatsTest {

   @Mock
   OrderRepo orderRepo;
   @InjectMocks
   ExportFormats exportFormats;
//   @Captor
//   p

   @Test
   void writeOrders() throws IOException {
      var order = new Order().setId(1L).setCreationDate(LocalDate.parse("2021-11-16"));
      when(orderRepo.findByActiveTrue()).thenReturn(Stream.of(order));
//      Writer writer = new StringWriter(); // this is a FAKE = implementation only for tests
      Writer writer = mock(Writer.class);

      exportFormats.writeOrders(writer);

      verify(writer).write(anyString());
//      String actualContent = writer.toString();
//      Assertions.assertThat(actualContent).isEqualTo("""
//          order_id;date
//          1;2021-11-16
//          """);
   }
}



