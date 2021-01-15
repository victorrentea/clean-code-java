package victor.training.java8;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
public class ExporterTest {

   @Test
   public void exportFile() {
      new Exporter().exportFile("test.csv", writer -> {
         try {
            writer.write("X");
         } catch (IOException e) {
            e.printStackTrace();
         }
      });

      // check on disk for a file named with 'X isndie
   }
}