package videostore.horror;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class Customer {

   private final String name;
   private final List<Rental> rentals = new ArrayList<>();

   public Customer(String name) {
      this.name = name;
   }

   public void addRental(Rental e) {
      rentals.add(e);
   }

   public String getName() {
      return name;
   }


   // TODO move to a StatementFormatter class.

   public String formatStatement() {
      int totalPoints = rentals.stream().mapToInt(Rental::calculateRenterPoints).sum();
		double totalPrice = rentals.stream().mapToDouble(Rental::computePrice).sum();

		return formatHeader()
				 + formatBody()
				 + formatFooter(totalPoints, totalPrice);
   }

	private String formatBody() {
		return rentals.stream().map(this::formatLine).collect(joining());
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatFooter(int totalPoints, double totalPrice) {
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + totalPoints + " frequent renter points";
	}

	private String formatLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}