package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category {
		REGULAR {
         @Override
         public double computePrice(int daysRented) {
            double price = 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            return price;
         }
      },
		CHILDRENS {
         @Override
         public double computePrice(int daysRented) {
            double price = 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            return price;
         }
      },
		NEW_RELEASE {
         @Override
         public double computePrice(int daysRented) {
            return daysRented * 3;
         }
      }
      ;

      public abstract double computePrice(int daysRented);
	}
   private final String title;
   private final Category category;



   public Movie(String title, Category category) {
      this.title = title;
      this.category = requireNonNull(category);
   }

   public Category getCategory() {
      return category;
   }

   public String getTitle() {
      return title;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Movie movie = (Movie) o;
      return Objects.equals(title, movie.title) && category == movie.category;
   }

   @Override
   public int hashCode() {
      return Objects.hash(title, category);
   }
}