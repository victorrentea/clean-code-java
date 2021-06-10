package videostore.horror;

import org.junit.Test;
import videostore.horror.Movie.Category;

class RegularMovie extends Movie {
   public RegularMovie(String title, Category category) {
      super(title, category);
   }
   @Override
   public double calculatePrice(int daysRented) {
      double price;
      price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
   @Override
   public int maxAllowedRentDays() {
      return 5;
   }
}
class NewReleaseMovie extends Movie {
   public NewReleaseMovie(String title, Category category) {
      super(title, category);
   }
   @Override
   public double calculatePrice(int daysRented) {
      return daysRented * 3;
   }

   @Override
   public int maxAllowedRentDays() {
      return 2;
   }
}

class ChildrenMovie extends Movie {

   public ChildrenMovie(String title, Category category) {
      super(title, category);
   }

   @Override
   public double calculatePrice(int daysRented) {
      double price;
      price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   @Override
   public int maxAllowedRentDays() {
      return 14;
   }
}


public abstract class Movie {
   public enum Category {
      CHILDREN,
      REGULAR,
      NEW_RELEASE
//      ,       ELDERS
   }

   public abstract double calculatePrice(int daysRented);
   public abstract int maxAllowedRentDays();

   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
      this.title = title;
      this.category = category;
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }

}
