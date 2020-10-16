package videostore.horror;
// <bean>
public class RentalPriceCalculator {

	public double calculateNewRelease(int daysRented) {
		return daysRented * 3;
	}

	public double calculateChildernPrice(int daysRented) {
		double price = 0;
		price += 1.5;
		if (daysRented > 3) {
			price += (daysRented - 3) * 1.5;
		}
		return price;
	}

	public double calculateRegularPrice(int daysRented) {
		double price = 0;
		price += 2;
		if (daysRented > 2) {
			price += (daysRented - 2) * 1.5;
		}
		return price;
	}

}
