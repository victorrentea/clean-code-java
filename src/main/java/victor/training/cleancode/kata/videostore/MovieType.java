package victor.training.cleancode.kata.videostore;

public enum MovieType {
    REGULAR(new RegularRentalStrategy()), // or (RegularPriceStrategy::class)
    // or"regularPriceStrategy" the name of the sprign bean + a @SpringBootTest with a for (enum values)
    NEW_RELEASE(new NewReleaseRentalStrategy()),
    CHILDREN(new ChildrenRentalStrategy());

    private final MovieRentalStrategy strategy;

    MovieType(MovieRentalStrategy strategy) {
        this.strategy = strategy;
    }

    public MovieRentalStrategy getStrategy() {
        return strategy;
    }
}
// applicationContext.getBean(enumV.strategyClazz)