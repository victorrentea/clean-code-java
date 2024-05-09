package victor.training.cleancode.kata.videostore;

import victor.training.cleancode.kata.videostore.enums.MovieType;

public record Movie(String title, MovieType movieType) {

	public double computeAmount( int daysRented) {
		double thisAmount=0;

		switch (movieType()) {
			case REGULAR:
				thisAmount = getRegularAmount(thisAmount, daysRented, 2, 2);
				break;
			case NEW_RELEASE:
				thisAmount += daysRented * 3;
				break;
			case CHILDREN:
				thisAmount = getRegularAmount(thisAmount, daysRented, 1.5, 3);
				break;
		}
		return thisAmount;
	}

	private double getRegularAmount(double thisAmount, int dr, double i, int i2) {
		thisAmount += i;
		if (dr > i2)
			thisAmount += (dr - i2) * 1.5;
		return thisAmount;
	}
}