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
class Square extends Rectangle {

}

//record Recta(int width, int height) {
//}
class ShapesPlay  {
    public static void main(String[] args) {
//        Recta recta = new Recta(2, 3);
//        recta.height();

        Square square = new Square();
        square.setHeight(3);
        square.setWidth(3);

        System.out.println(square.area());
        System.out.println(square.perimeter());
    }
}