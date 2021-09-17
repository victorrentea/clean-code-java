package victor.training.pure;

import javax.persistence.Version;

public class PerziInocentaCandOVeziPrimaData {

   public static void main(String[] args) {
      DateMutabile date = new DateMutabile();


      // aici horror
//      new Thread(()-> {date.setX(date.getX() + 1);}).start();

//      new Thread(()-> {date.setX(date.getX() + 1);}).start();

      // "SELECT FOR UPDATE "
//      @Version (hibernate)

      // RACE BUG


   }

}

class DateMutabile {
   private int x = 0;

   public int getX() {
      return x;
   }

//   public void setX(int x) {
//      this.x = x;
//   }
}