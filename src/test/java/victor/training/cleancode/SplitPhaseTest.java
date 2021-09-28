package victor.training.cleancode;

import org.junit.Test;

import static org.junit.Assert.*;

public class SplitPhaseTest {

   @Test
   public void parse() {
      new SplitPhase();
      String[] orderData = "a-CHR 2".split("\\s+");
      String productCode = orderData[0].split("-")[1];
      int itemCount = Integer.parseInt(orderData[1]);
      ParsedOrderLine line = new ParsedOrderLine(productCode, itemCount);
      ParsedOrderLine parse = line;
      assertEquals("CHR", parse.getProductCode());
      assertEquals(2, parse.getItemCount());
   }
}