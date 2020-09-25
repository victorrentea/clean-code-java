package videostore.horror;


// Daniel, dupa 2 min: "nu facem si noi Movie abstract" <- extinsa de ChildrenMovie, RegularMovie...
public class Movie {

   enum Category {
      REGULAR,
      NEW_RELEASE,
      CHILDRENS
   }

   private final String title;
   private final Category priceCode;

   public Movie(String title, Category priceCode) {
      this.title = title;
      this.priceCode = priceCode;
   }

   public Category getPriceCode() {
      return priceCode;
   }

   public String getTitle() {
      return title;
   }

   ;
}