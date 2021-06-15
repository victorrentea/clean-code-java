package videostore.horror;

public class Movie {
	enum Category {
		REGULAR,
		NEW_RELEASE,
		CHILDRENS
	}
   public static final int CHILDRENS = 2;
   public static final int REGULAR = 0;
   public static final int NEW_RELEASE = 1;

   private String _title;
   private Category _priceCode;

   public Movie(String title, Category priceCode) {
      _title = title;
      _priceCode = priceCode;
   }

   public Category getPriceCode() {
      return _priceCode;
   }

   public void setPriceCode(Category arg) {
      _priceCode = arg;
   }

   public String getTitle() {
      return _title;
   }

   ;
}