package victor.training.cleancode.java.refactoring.inheritance;

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
class Square {}