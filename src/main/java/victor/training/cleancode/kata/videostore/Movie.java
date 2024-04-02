package victor.training.cleancode.kata.videostore;


public abstract class Movie{
    private final String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double computePrice(int daysRented);



    public int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}