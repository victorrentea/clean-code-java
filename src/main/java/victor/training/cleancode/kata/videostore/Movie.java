package victor.training.cleancode.kata.videostore;


public abstract class Movie{
    public Movie(String title) {
        this.title = title;
    }

    private String title;
    public abstract double computePrice(int daysRented);

    public int getFrequentRenterPoints(){
        return 1;
    }
}