package victor.training.cleancode.kata.videostore;

public record Rental (Movie movie, Integer days){

    double getPrice() {
        double thisAmount = 0;
        switch (movie().priceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (days() > 2)
                    thisAmount += (days() - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += days() * 3;
            case CHILDRENS -> {
                thisAmount += 1.5;
                if (days() > 3)
                    thisAmount += (days() - 3) * 1.5;
            }
        }
        return thisAmount;
    }
}
