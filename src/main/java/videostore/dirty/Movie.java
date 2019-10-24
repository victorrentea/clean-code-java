package videostore.dirty;

import java.util.function.Function;

public class Movie {

    enum Category {
		CHILDREN{
            @Override
            public double computePrice(int daysRented) {
                return 0;
            }
        },
		REGULAR(PriceCalculators::computeRegular),
		NEW_RELEASE (dr -> 2d);
public final Function<Category, Double> priceAlgo;
Category(Function<Category, Double> priceAlgo) {

    this.priceAlgo = priceAlgo;
}
    }

    private final String title;
    private final Category category;


    public Movie(final String title, final Category category) {
        this.title = title;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }
}

