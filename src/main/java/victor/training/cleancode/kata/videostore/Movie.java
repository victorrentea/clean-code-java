package victor.training.cleancode.kata.videostore;

public record Movie(String title, Integer priceCode)
{
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	public double getRentCost(int daysRented){
		double totalAmount = 0;
		switch (priceCode) {
			case Movie.REGULAR:
				totalAmount += 2;
				if (daysRented > 2)
					totalAmount += (daysRented - 2) * 1.5;
				break;

			case Movie.NEW_RELEASE:
				totalAmount += daysRented * 3;
				break;

			case Movie.CHILDREN:
				totalAmount += 1.5;
				if (daysRented > 3)
					totalAmount += (daysRented - 3) * 1.5;
				break;

		}
		return totalAmount;
	}
}