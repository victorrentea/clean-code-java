package victor.training.refactoring.inheritance;

import lombok.Data;

@Data
class Rectangle {
    private int width;
    private int height;

    public int area() {
        return width * height;
    }
    public int perimeter() {
        return 2 * (width + height);
    }
}

// TODO cu area si perimeter
class Square /*extends Rectangle*/ {
    private Rectangle rectangle = new Rectangle();

    public void setEdge(int edge) {
        rectangle.setHeight(edge);
        rectangle.setWidth(edge);
    }
    public int area() {
        return rectangle.area();
    }
}
class CodTest{
    public static void main(String[] args) {
        printStats(new Rectangle());
        printStats(new Square());
    }
    public static void printStats(Rectangle r) {
        r.setWidth(3);
        r.setHeight(2);
        System.out.println(r.area());
    }
    public static void printStats(Square s) {
        s.setEdge(2);
        System.out.println(s.area());
    }
}