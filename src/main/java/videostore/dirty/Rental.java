package videostore.dirty;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    interface MovieTypeFunctions {
        double computePrice();
        double computePrice2();
        double computePrice3();
    }

    public static class NewReleaseMovieFunctions implements MovieTypeFunctions {

    }

    public double computePrice() {
        switch (getMovie().getType()) {
            case REGULAR: return computeRegularPrice();
            case NEW_RELEASE: return computeNewReleasePrice();
            case CHILDREN:return computeChildrenPrice();
			default: throw new IllegalStateException("Unexpected value: " + getMovie().getType());
		}
    }
    public double computePrice2() {
        switch (getMovie().getType()) {
            case REGULAR: return computeRegularPrice();
            case NEW_RELEASE: return computeNewReleasePrice();
            case CHILDREN:return computeChildrenPrice();
			default: throw new IllegalStateException("Unexpected value: " + getMovie().getType());
		}
    }
    public double computePrice3() {
        switch (getMovie().getType()) {
            case REGULAR: return computeRegularPriceX();
            case NEW_RELEASE: return computeNewReleasePriceY();
            case CHILDREN:return computeChildrenPriceZ();
			default: throw new IllegalStateException("Unexpected value: " + getMovie().getType());
		}
    }

	private double computeChildrenPrice() {
		double price = 1.5;
		if (daysRented > 3)
			price += (daysRented - 3) * 1.5;
		return price;
	}

	private int computeNewReleasePrice() {
		return daysRented * 3;
	}

	private double computeRegularPrice() {
		double price = 0;
		price += 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}

	public int computeFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        boolean isNewRelease = movie.getType() == Movie.Type.NEW_RELEASE;
        if (isNewRelease && daysRented >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }


}