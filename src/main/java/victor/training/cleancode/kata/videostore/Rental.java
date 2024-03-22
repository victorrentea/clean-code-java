package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {


    double calculateCosts() {
        double cost = 0;
        // determine amounts for every line
        switch (movie.category()) {
        case REGULAR -> {
            cost += 2;
            if (rentalDays > 2)
                cost += (rentalDays - 2) * 1.5;
        }
        case NEW_RELEASE -> cost += rentalDays * 3;
        case CHILDRENS -> {
            cost += 1.5;
            if (rentalDays > 3)
                cost += (rentalDays - 3) * 1.5;
        }
        }
        return cost;
    }
}
