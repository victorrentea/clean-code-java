package victor.training.refactoring.inheritance;

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
class Square {
   private final Rectangle rectangle;
   public Square(int edge) {
      rectangle = new Rectangle();
      rectangle.setHeight(edge);
      rectangle.setWidth(edge);
   }

   public int area() {
      return rectangle.area();
   }

   public int perimeter() {
      return rectangle.perimeter();
   }
}

//record Recta(int width, int height) {
//}
class ShapesPlay {
   public static void main(String[] args) {
//        Recta recta = new Recta(2, 3);
//        recta.height();

      Square square = new Square(3);


      altaMetoda(square);
   }

   private static void altaMetoda(Square square) {
      System.out.println(square.area());
      System.out.println(square.perimeter());
   }
}