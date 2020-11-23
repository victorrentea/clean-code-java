package videostore.dirty;

public enum MovieCategory {
   CHILDREN {
      @Override
      public double calculatePrice(int daysRented) {
         double price = 1.5;
         if (daysRented > 3)
            price += (daysRented - 3) * 1.5;;
            return price;
      }
   },
   REGULAR {
      @Override
      public double calculatePrice(int daysRented) {
         double price = 2;
         if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
         return price;
      }
   },
   NEW_RELEASE {
      @Override
      public double calculatePrice(int daysRented) {
         return daysRented * 3;
      }
   };

   public abstract double calculatePrice(int daysRented);
}
