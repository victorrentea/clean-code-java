package victor.training.refactoring;

public class PureOrNot {
   public static void main(String[] args) {
      CuDate cuDate = new CuDate();
      System.out.println(m(cuDate));
      System.out.println(m(cuDate));

      System.out.println(cuDate.compute());
      cuDate.setS("Tzeapa");
      System.out.println(cuDate.compute());
   }

   public static String m(CuDate date) { // nu e pura
      date.incrementS();
      return date.getS();
   }
}

class CuDate {
   private String s = "";
   int x;

   public String getS() {
      return s;
   }

   public CuDate setS(String s) {
      this.s = s;
      return this;
   }
   public void incrementS() {
      s += "0";
   }
   public String compute() {
      // ****
      // in rationamentul daca e pura sau nu, trebuie considerate
      // cele doua campuri ca intrari, ca parametrii
      // ****

      // Daca instanta gazda devine immutable atunci e beton:
      // mai ai de analizat doar parametrii luati in functie.
      return s + x;
   }
}