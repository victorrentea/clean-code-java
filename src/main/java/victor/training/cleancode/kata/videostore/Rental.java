package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, Integer rentDays) {

    public double computePrice() {
        double thisAmount = 0;
        switch (movie.movieType()) {

            case REGULAR -> {
                thisAmount += 2;
                if (rentDays > 2)
                    thisAmount += (rentDays - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += rentDays * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (rentDays > 3)
                    thisAmount += (rentDays - 3) * 1.5;
            }
        }
        return thisAmount;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints=0;
        frequentRenterPoints++;

        // add bonus for a two day new release rental
        if (movie().movieType() == MovieType.NEW_RELEASE && rentDays() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;

    }
}
