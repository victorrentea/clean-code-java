package victor.training.refactoring.inheritance;

import org.apache.commons.lang.NotImplementedException;

class Rectangle {
   private int width;
   private int height;

   public int area() {
      return width * height;
   }

   public int perimeter() {
      return 2 * (width + height);
   }

   public int getHeight() {
      return height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getWidth() {
      return width;
   }

   public void setWidth(int width) {
      this.width = width;
   }
}

// TODO
class Square extends Rectangle {
   public Square(int edge) {
      setWidth(edge);
      setHeight(edge);
   }

   public void setWidth(int w) {
//      throw new NotImplementedException(); // pervers caci crapa doar la runtime
      super.setHeight(w);
      super.setWidth(w);
   }
   public void setHeight(int h) {
//      throw new NotImplementedException();
      super.setHeight(h);
      super.setWidth(h);
   }

}

//record Recta(int width, int height) {
//}
class ShapesPlay {
   public static void main(String[] args) {
//        Recta recta = new Recta(2, 3);
//        recta.height();

      Rectangle square = new Square(3);


      altaMetoda(square);
   }

   private static void altaMetoda(Rectangle square) {
      square.setHeight(3);
      square.setWidth(4);

      System.out.println(square.area());
      System.out.println(square.perimeter());
   }
}