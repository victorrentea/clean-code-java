package videostore.horror;

public enum PriceFactor {
  CHILDREN{
    @Override
    public double computePrice(int rentalDays) {
      double price;
      price = 1.5;
      if (rentalDays > 3)
        price += (rentalDays - 3) * 1.5;
      return price;
    }
  },
  REGULAR {
    @Override
    public double computePrice(int rentalDays) {
      double price;
      price = 2;
      if (rentalDays > 2)
        price += (rentalDays - 2) * 1.5;
      return price;
    }
  },
  NEW_RELEASE {
    @Override
    public double computePrice(int rentalDays) {
      return rentalDays * 3;
      //
    }
  }
//  ,ELDERS{
//    @Override
//    public double computePrice(int rentalDays) {
//      return 0;
//    }
//  } // enum doesn't compile!! missing implem
  ;


  public abstract double computePrice(int rentalDays);
}
