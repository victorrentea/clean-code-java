package victor.training.refactoring;

public class ABInteractor {
   public A a = new A();
   public B b = new B();

   public ABInteractor() {
   }

   public void fa() {
      a.f();
   }

   public void fab() {
      a.f();
      b.f();
   }
}