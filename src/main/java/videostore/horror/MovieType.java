package videostore.horror;

public enum MovieType {
  CHILDREN/*{
    @Override
    public double getPrice(int daysRented) {
      double result =  1.5;
      if (daysRented > 3)
        result += (daysRented - 3) * 1.5;
      return result;
    }
  }*/,
  REGULAR/* {
    @Override
    public double getPrice(int daysRented) {
      double result = 2;
      if (daysRented > 2)
        result += (daysRented - 2) * 1.5;
      return result;
    }
  }*/,
  NEW_RELEASE/* {
    @Override
    public double getPrice(int daysRented) {
      return daysRented * 3;
    }
  }*/,
  ELDER;

//  public abstract double getPrice(int daysRented);
}
