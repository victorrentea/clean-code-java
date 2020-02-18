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
    private final Rectangle rectangle = new Rectangle();
    public int area() {
        return rectangle.area();
    }
    public int perimeter() {
        return rectangle.perimeter();
    }

    public void setEdge(int edge) {
        rectangle.setWidth(edge);
        rectangle.setHeight(edge);
    }
}

class CodClient {
    public static void main(String[] args) {
        Square s = new Square();
        m(s);
    }

    private static void m(Square s) {
        s.setEdge(4);
        System.out.println(s.area());
    }
}