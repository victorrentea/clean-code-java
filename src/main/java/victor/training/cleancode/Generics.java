package victor.training.cleancode;

import java.util.HashMap;
import java.util.Map;

public class Generics {
  public static void main(String[] args) {
    HashMap<String, String> ff = new HashMap<>();
    ff.put("key", "value");
    Object map = ff;


    f(map);
  }

  private static void f(Object map) {
    if (map instanceof Map) {
      @SuppressWarnings("unchecked") Map<String, String> map2 = (Map<String, String>) map;
      System.out.println(map2.get("key") + 1);

      @SuppressWarnings("unchecked") Map<String, Integer> map3= (Map<String, Integer>) map;
      System.out.println(map3.get("key") + 1); // crashes with class cast
    }
  }
}
