package videostore.dirty;
class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		if(movie == null) {
			throw new IllegalArgumentException();
		}
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int computeRenterPoints() {
		int points = 1;
	
		if (movie.isNewRelease() && daysRented >= 2) {
			points ++;
		}
		return points;
	}

	public double computePrice() {
		
		
		
		double thisAmount = 0;
		switch (getMovie().getCategory()) {
		case REGULAR:
			thisAmount += 2;
			if (getDaysRented() > 2)
				thisAmount += (getDaysRented() - 2) * 1.5;
			break;
		case NEW_RELEASE:
			thisAmount += getDaysRented() * 3;
			break;
		case CHILDRENS:
			thisAmount += 1.5;
			if (getDaysRented() > 3)
				thisAmount += (getDaysRented() - 3) * 1.5;
			break;
		case VIEJAS: //forget
			break;
		default:
			throw new IllegalArgumentException();
		
		}
		return thisAmount;
	}
}