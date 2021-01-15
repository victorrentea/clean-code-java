package victor.training.java8;

import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.*;

public class OrderContentWriterTest {

   @Test
   public void write() {
      Writer writer = new StringWriter();
      new OrderContentWriter(null) .write(writer);
      String fileContent = writer.toString();
   }
}