package videostore.dirty;


import java.util.function.Function;
public class Movie {
   public enum Category {
      CHILDRENS("Movies for children") {
         @Override
         public double determinePrice(int daysRented) {
            double price = 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            return price;
         }
      },
      REGULAR("Regular") {
         @Override
         public double determinePrice(int daysRented) {
            double price = 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            return price;
         }
      },
      NEW_RELEASE("File noi") {
         @Override
         public double determinePrice(int daysRented) {
            return daysRented * 3;
         }
      };

      // daca esti curios: https://www.youtube.com/watch?v=F02LKnWJWF4
//      private final Function<Integer, Double> algorithm;

      private final String label;

      Category(String label) {

         this.label = label;
      }

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