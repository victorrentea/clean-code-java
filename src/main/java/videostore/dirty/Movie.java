package videostore.dirty;

// TEMA1: replace SWITCH with Polymorphism

/*class NewReleaseMovie extends Movie {
   @Overide
   private double computePrice() {
      double price = 1.5;
      if (getDaysRented() > 3) {
         price += (getDaysRented() - 3) * 1.5;
      }
      return price;
   }
}
abstract */public class Movie {

   enum Category {
      CHILDRENS {
         // TEMA2:
//         @Override
//         public double computePrice() {
//            return 0;
//         }
      },
      // TEMA3
      REGULAR(/*OClasa::computeRegularPrice*/),
      NEW_RELEASE,
      ELDERS

//      abstract public double computePrice();
   }

//   protected abstract double computePrice();

   private final String title;
   private final Category category;

   // Bounded Context -----> "in universul Movie, CHILDRENS este category"
   // Acelasi CHILDREN in universul Pricing este priceCode.
   public Movie(String title, Category category) {
      if (category == null) {
         throw new IllegalArgumentException("null priceCode");
      }
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