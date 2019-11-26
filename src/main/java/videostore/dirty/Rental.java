package videostore.dirty;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int computeBonusRenterPoints() {
        int frequentRenterPoints = 1;
        boolean isNewRelease = movie.getMovieType() == MovieType.NEW_RELEASE;
        if (isNewRelease && daysRented >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }


    interface MoviePriceCalculator {
        double computePrice();
        double computePrice2();
        double computePrice3();
    }
    class NewReleasePriceCalculator implements MoviePriceCalculator {
        public double computePrice() {
            return 0;
        }
        public double computePrice2() {
            return 0;
        }
        public double computePrice3() {
            return 0;
        }
    }
    class RegularPriceCalculator implements MoviePriceCalculator {
        public double computePrice() {
            return 0;
        }
        public double computePrice2() {
            return 0;
        }
        public double computePrice3() {
            return 0;
        }
    }


    public MoviePriceCalculator selectPriceCalculator(MovieType type) {
        switch (type) {
            case NEW_RELEASE: return new NewReleasePriceCalculator();
        }
    }

    public double computePrice() {
        switch (getMovie().getMovieType()) {
            case REGULAR: return computeRegularMoviePrice();
            case NEW_RELEASE: return computeNewReleaseMoviePrice();
            case CHILDREN: return computeChildrenMoviePrice();
            default:
                throw new IllegalStateException("JDD Unexpected value: " + getMovie().getMovieType());
        }
    }
    public double computePrice2() {
        switch (getMovie().getMovieType()) {
            case REGULAR: return computeRegularMoviePrice();
            case NEW_RELEASE: return computeNewReleaseMoviePrice();
            case CHILDREN: return computeChildrenMoviePrice();
            default:
                throw new IllegalStateException("JDD Unexpected value: " + getMovie().getMovieType());
        }
    }
    public double computePrice3() {
        switch (getMovie().getMovieType()) {
            case REGULAR: return computeRegularMoviePrice();
            case NEW_RELEASE: return computeNewReleaseMoviePrice();
            case CHILDREN: return computeChildrenMoviePrice();
            default:
                throw new IllegalStateException("JDD Unexpected value: " + getMovie().getMovieType());
        }
    }

	private double computeRegularMoviePrice() {
		double price = 2;
		if (getDaysRented() > 2)
			price += (getDaysRented() - 2) * 1.5;
		return price;
	}

	private double computeNewReleaseMoviePrice() {
		return getDaysRented() * 3;
	}

	private double computeChildrenMoviePrice() {
        double price = 0;
        price += 1.5;
        if (getDaysRented() > 3)
            price += (getDaysRented() - 3) * 1.5;
        return price;
    }
}