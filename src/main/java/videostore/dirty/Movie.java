package videostore.dirty;

public class Movie {

   private final String title;
   private final MovieCategory category;

   public Movie(String title, MovieCategory category) {
      this.title = title;
      this.category = category;
   }

   public MovieCategory getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }

}


//
//
//class A {
//   private final B b;
//
//   A(B b) {
//      this.b = b;
//   }
//
//   public void method() {
//       b.method();
//   }
//   public void method2() {
//       b.method();
//   }
//}
//
//class A2 {
//   private final B b;
//
//   A2(B b) {
//      this.b = b;
//   }
//
//   public void method() {
//       b.method();
//   }
//}
//
//class B{
//   public B() {}
//   public void method() {
//
//   }
//}