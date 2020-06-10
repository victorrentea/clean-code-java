package victor.training.refactoring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SplitVariable {

   public int discount(int price, int quantity) {
      return price
          - priceDiscount(price)
          - quantityDiscount(quantity);
   }

   private int quantityDiscount(int quantity) {
      if (quantity > 100) {
         return 1;
      } else {
         return 0;
      }
   }

   private int priceDiscount(int price) {
      return price > 50 ? 2 : 0;
   }


   @Test
   public void test() {
      assertEquals(1, discount(1, 1));
      assertEquals(50, discount(50, 1));
      assertEquals(49, discount(51, 1));
      assertEquals(0, discount(1, 101));
      assertEquals(49, discount(50, 101));
      assertEquals(48, discount(51, 101));
   }

}