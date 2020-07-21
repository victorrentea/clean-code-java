package videostore.horror;

public class Movie {


   enum Category {
      CHILDREN{
         @Override
         double determinePrice(int daysRented) {
            double price = 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            return price;
         }
      },
      REGULAR {
         @Override
         double determinePrice(int daysRented) {
            double price = 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            return price;
         }
      },
      NEW_RELEASE {
         @Override
         double determinePrice(int daysRented) {
            return  daysRented * 3;
         }
      };

      abstract double determinePrice(int daysRented);
   }
   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
      this.title = title;
      this.category = category;
   }

   public Category getCategory() {
      return category;
   }

   public boolean isNewRelease() {
      return getCategory() == Category.NEW_RELEASE;
   }

   public String getTitle() {
      return title;
   }

}
