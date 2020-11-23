package videostore.dirty;

public class Movie {
   public static final int CATEGORY_CHILDRENS = 2;
   public static final int CATEGORY_REGULAR = 0;
   public static final int CATEGORY_NEW_RELEASE = 1;
   
   enum Category {
		CHILDREN(2),
		REGULAR(0),
		NEW_RELEASE(1);
      private final int code;
      Category(int code) {
         this.code = code;
      }
      public int getCode() {
         return code;
      }
   }
   
   private String title;
   private Category priceCode;

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