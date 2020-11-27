package videostore.dirty;


public class Movie {
   public enum Category {
      CHILDRENS {
         @Override
         public double determinePrice(int daysRented) {
            double price = 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            return price;
         }
      },
      REGULAR {
         @Override
         public double determinePrice(int daysRented) {
            double price = 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            return price;
         }
      },
      NEW_RELEASE {
         @Override
         public double determinePrice(int daysRented) {
            return daysRented * 3;
         }
      };

      public abstract double determinePrice(int daysRented);
   }
   private final String title;

   private final Category category;
   public Movie(String title, Category category) {
      this.title = title;
      this.category = category;
   }

   public boolean isNewRelease() {
      return category == Category.NEW_RELEASE;
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }
}