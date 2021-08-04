package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class AltEnterBAD {
   public static void main(String[] args) {

      int x = 1;

      List<Integer> ints = new ArrayList<>();
//      ints.stream().forEach(n -> {
//         if (n > x) x = n; // ALT enter provides s**ty suggestion
//      });


      x = ints.stream().mapToInt(i->i).max().orElse(1);
   }
}
