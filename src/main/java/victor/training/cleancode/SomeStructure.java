package victor.training.cleancode;

// Option1 for working with  large immutable object : make a builder
class SomeStructureBuilder {
   private String x;
   private String y;
   private String y1;
   private String y4;
   private String y2;
   private String y6;
   private String y7;
   private String y8;

   public SomeStructureBuilder setX(String x) {
      this.x = x;
      return this;
   }

   public SomeStructureBuilder setY(String y) {
      this.y = y;
      return this;
   }

   public SomeStructureBuilder setY1(String y1) {
      this.y1 = y1;
      return this;
   }

   public SomeStructureBuilder setY4(String y4) {
      this.y4 = y4;
      return this;
   }

   public SomeStructureBuilder setY2(String y2) {
      this.y2 = y2;
      return this;
   }

   public SomeStructureBuilder setY6(String y6) {
      this.y6 = y6;
      return this;
   }

   public SomeStructureBuilder setY7(String y7) {
      this.y7 = y7;
      return this;
   }

   public SomeStructureBuilder setY8(String y8) {
      this.y8 = y8;
      return this;
   }

   public SomeStructure build() {
      return new SomeStructure(x,y, y1,y4,y2,y6,y7,y8);
//      return new SomeStructure(this); // pass the builder in
   }
}



// Option2 (for large immutable objects) :
//  BREAK THEM
// any immutable object with more than ~5 required fields  should be broken down

public class SomeStructure {
   private final String x;
   private final String y;
   private final String y1;
   private final String y4;
   private final String y2;
   private final String y6;
   private final String y7Opt;
   private final String y8;

//   {
//      SomeStructure someStructure = new SomeStructure(x, y, y1, y4, y2, y6, y7Opt, y8)
//          .setY("y")
//          .setX("x")
//          .setY1("y1");
//   }

   public SomeStructure(String x, String y, String y1, String y4, String y2, String y6, String y7, String y8) {
      this.x = x;
      this.y = y;
      this.y1 = y1;
      this.y4 = y4;
      this.y2 = y2;
      this.y6 = y6;
      this.y7Opt = y7;
      this.y8 = y8;
   }

   public String getX() {
      return x;
   }

   public String getY() {
      return y;
   }

   public String getY1() {
      return y1;
   }

   public String getY4() {
      return y4;
   }

   public String getY2() {
      return y2;
   }

   public String getY6() {
      return y6;
   }

   public String getY7Opt() {
      return y7Opt;
   }

   public String getY8() {
      return y8;
   }
}



