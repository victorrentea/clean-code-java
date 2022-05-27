package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
    private final String title;
    private final Category category;

    public Movie(String title, Category category) {
        this.title = title;
        this.category = requireNonNull(category);
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    enum Category {
        REGULAR{
            @Override
            public double computePrice(int daysRented) {
                double price = 2;
                if (daysRented > 2)
                    price += (daysRented - 2) * 1.5;
                return price;
            }
        },
        NEW_RELEASE {
            @Override
            public double computePrice(int daysRented) {
                return daysRented * 3;
            }
        },
        CHILDREN{
            @Override
            public double computePrice(int daysRented) {
                double price = 1.5;
                if (daysRented > 3)
                    price += (daysRented - 3) * 1.5;
                return price;
            }
        },
//        BABACI,
        ;

        abstract public double computePrice(int daysRented);
    }

    ;
}