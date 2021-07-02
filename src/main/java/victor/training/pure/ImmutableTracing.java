package victor.training.pure;

public class ImmutableTracing {

   public static void main(String[] args) {
      new MutableHell().h();
   }
}

class Data {
   private int x;

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }
}

class MutableHell {

   public void h() {
      Data data = new Data();
      data.setX(1);
      // more code
      g(data);
   }

   public void g(Data data) {
      data.setX(2);
      // more code
      evil(data);
      // more code
      f(data);
   }

   private void evil(Data data) {
      deeperEvil(data);
   }

   private void deeperEvil(Data data) {
      data.setX(3);
   }

   public void f(Data data) {
      if (data.getX() == 1) {
         System.out.println("Launch missile");
      }
   }

}