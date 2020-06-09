package victor.training.cleancode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class BouleanParameters {

   public static void main(String[] args) {
      // The method is called from various foreign places in the codebase
      bigUglyMethod(2, 1);
      bigUglyMethod(2, 1);
      bigUglyMethod(2, 1);
      bigUglyMethod(2, 1);
      bigUglyMethod(2, 1);

      // TODO From my use-case, I call it too, to do more within:
      bigUglyMethodMyUC(2, 1);

   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(a);
      afterLogic(b);
   }

   static void bigUglyMethodMyUC(int b, int a) {
      beforeLogic(a);
      // in matzele ei: vrei sa faci si tu - doar cand tu chemi functia
      System.out.println("ASTA");
      afterLogic(b);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void beforeLogic(int a) {
      System.out.println("Complex Logic " + a);
      System.out.println("Complex Logic " + a);
      System.out.println("Complex Logic " + a);
   }


   // ============== "BOSS" LEVEL: A lot harder to break down =================

   static void bossLevelStuff(List<Integer> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");

//      Path p = new File("2gb.txt").toPath();
//      Stream<String> lines = Files.lines(p)
//          .filter()
//      .distinct() // Out of mem pentru streamuri foarte lungi
//          .am;

      // TODO localize
      for (int task : tasks) {
         System.out.println("Logic4 " + task);
      }
      for (int task : tasks) {
         // TODO HERE, when call this method, I want MY own custom code to run here
         // PP: ca logica noua depinde de task4: adica vrea sa proceseze elementele dupa ce Logic4 a rulat
      }

      int i = 0;
      for (int task : tasks) {
         i++;
         System.out.println("Logic5 " + i);
      }
      int j = 1;
      System.out.println("Logic6 " + j++);
      System.out.println("Logic7");
   }

   static void bossLevelStuffNoFluff() {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7");
   }

   static void bossLevelNoStuff() {
      System.out.println("Logic1");
      System.out.println("Logic7");
   }
}
