package videostore.dirty;


public class Movie {
   public enum Category {
      CHILDRENS(new ChildrenPriceCalculator()),
      REGULAR(new RegularPriceCalculator()),
      NEW_RELEASE(new NewReleasePriceCalculator());
      private final PriceCalculator calculator;

      Category(PriceCalculator calculator) {
         this.calculator = calculator;
      }

      public PriceCalculator getCalculator() {
         return calculator;
      }
// f(daysRented, category): double

//      public void determinePrice(int daysRented) {
//
//      }
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