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
        REGULAR,
        NEW_RELEASE,
        CHILDREN,
    }

    ;
}