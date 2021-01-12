package victor.training.cleancode;

public class DeCeNuInterfeteDeLaStart {
}


class A {
   private final IB b;

   A(IB b) {
      this.b = b;
   }
   public void method() {
       b.method();
   }
}

class B implements IB {
   @Override
   public void method() {
       // implem 2
   }
}
class B2 implements IB {

   @Override
   public void method() {
      // implem 1
   }
}
