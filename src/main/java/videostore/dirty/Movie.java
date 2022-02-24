package videostore.dirty;

public class Movie {
   boolean isNewRelease() {
      return getPriceCategory() == PriceCategory.NEW_RELEASE;
   }

   enum PriceCategory {
      REGULAR,
      NEW_RELEASE,
      CHILDREN
   }

   private final String title;
   private final PriceCategory priceCategory;

   public Movie(String title, PriceCategory priceCategory) {
      this.title = title;
      this.priceCategory = priceCategory;
   }

   public PriceCategory getPriceCategory() {
      return priceCategory;
   }

   public String getTitle() {
      return title;
   }
}