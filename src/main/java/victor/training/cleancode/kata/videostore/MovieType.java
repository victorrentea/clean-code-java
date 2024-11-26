package victor.training.cleancode.kata.videostore;

public enum MovieType {
    REGULAR(new RegularRentalStrategy()),
    NEW_RELEASE(new NewReleaseRentalStrategy()),
    CHILDREN(new ChildrenRentalStrategy());

    //    abstract double
    private final MovieRentalStrategy strategy;

    MovieType(MovieRentalStrategy strategy) {
        this.strategy = strategy;
    }

    public MovieRentalStrategy getStrategy() {
        return strategy;
    }
}