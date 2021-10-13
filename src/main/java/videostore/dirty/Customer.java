package videostore.dirty;

import org.springframework.data.jpa.repository.Query;
import videostore.dirty.Movie.PriceCode;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


interface Incredibil {
//	@Query("""
//<asd{"<b>
//
//}
//SELECT from
//Enter frate fara
//A fost odata ca-n povesti,
//A fost ca nicioadata
//Din rude mari imparatesti
//O preafrumoasa fata
//
//Strofa 2
//""")
	public void method();
}
class Customer {
   private String name;
   private List rentals = new ArrayList();

   public Customer(String name) {
      this.name = name;
   }

   ;

   public void addRental(Rental arg) {
      rentals.add(arg);
   }

   public String getName() {
      return name;
   }

   public String statement() {
      double totalAmount = 0;
      int frequentRenterPoints = 0;
      Iterator rentals = this.rentals.iterator();
      String result = "Rental Record for " + getName() + "\n";
      while (rentals.hasNext()) {
         double thisAmount = 0;
         Rental each = (Rental) rentals.next();
         // determine amounts for each line
         thisAmount += computePrice(each);
         // add frequent renter points
         frequentRenterPoints++;
         // add bonus for a two day new release rental
         if ((each.getMovie().getPriceCode() == Movie.PriceCode.CATEGORY_NEW_RELEASE)
             && each.getDaysRented() > 1)
            frequentRenterPoints++;
         // show figures for this rental
         result += "\t" + each.getMovie().getTitle() + "\t"
                   + thisAmount + "\n";
         totalAmount += thisAmount;
      }
      // add footer lines
      result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
      result += "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";
      return result;
   }

//	EnumMap<PriceCode, Function<Rental, Double>> map;
   private double computePrice(Rental rental) {
//		return rental.getMovie().getPriceCode() == PriceCode.CATEGORY_REGULAR ? 1 : 2;
//
      return rental.getMovie().getPriceCode().priceCalculationFunction.apply(rental);

//		return switch (rental.getMovie().getPriceCode()) {
//			case CATEGORY_REGULAR,CATEGORY_NEW_RELEASE -> computeRegularPrice(rental);
//			case CATEGORY_CHILDRENS -> computeChildrenPrice(rental);
//			default -> 0;
//		};
   }
//	record X(String action, Consumer<SeleniumPage> checked) {
//	}

   public static double computeRegularPrice(Rental rental) {
		double price = 2;
      if (rental.getDaysRented() > 2)
         price += (rental.getDaysRented() - 2) * 1.5;
      return price;
   }

   public static double computeNewReleasePrice(Rental rental) {
      return rental.getDaysRented() * 3;
   }

   public static double computeChildrenPrice(Rental rental) {
		double price = 1.5;
      if (rental.getDaysRented() > 3)
         price += (rental.getDaysRented() - 3) * 1.5;
      return price;
   }
}