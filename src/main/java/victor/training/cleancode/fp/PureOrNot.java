package victor.training.cleancode.fp;

public class PureOrNot {

  public static int method(Data data, Integer y) {
    y++; // y = Integer.valueOf(y.intValue() + 1); // new object on the heap if y is Integer
//    data.setX(data.getX() + 1); // changing the object on the Heap that both ME and my CALLER reference
    return data.getX() + y;
  }

  public static void main(String[] args) {
    Data d = new Data();
    d.setX(7);
    Integer y = 1; // wrapper types are immutable in Java Long, Integer, etc also String

    int newY = method(d, y);

    System.out.println(y);
    System.out.println(d);
  }

  static class Data {
    private String input;
    private Integer x;

    public Integer getX() {
      return x;
    }

    public Data setX(Integer x) {
      this.x = x;
      return this;
    }

    @Override
    public String toString() {
      return "Data{" +
             "input='" + input + '\'' +
             ", x=" + x +
             '}';
    }
  }
}
