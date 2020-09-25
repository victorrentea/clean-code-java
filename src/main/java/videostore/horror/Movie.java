package videostore.horror;


// Daniel, dupa 2 min: "nu facem si noi Movie abstract" <- extinsa de ChildrenMovie, RegularMovie...
public class Movie {

	enum Category {
   REGULAR(0),
   NEW_RELEASE(1),
	CHILDRENS(2);

		private final int code; // daca era din DB, UI, Fisier

		Category(int code) {

			this.code = code;
		}
	}

	public static final int CHILDRENS = 2;
   public static final int REGULAR = 0;
   public static final int NEW_RELEASE = 1;

   private final String _title;
   private final Integer _priceCode;

   public Movie(String title, Integer priceCode) {
      _title = title;
      _priceCode = priceCode;
   }

   public Integer getPriceCode() {
      return _priceCode;
   }

	public String getTitle() {
      return _title;
   }

   ;
}