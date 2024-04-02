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
}
