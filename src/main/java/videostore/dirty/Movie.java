package videostore.dirty;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Movie {
    private final String title;
    private final MovieCategory category;

    public Movie(String title, MovieCategory category) {
        // reflex: daca ai vreun obiect fara setteri (imutabil) incerci sa-i pui validari prin ctor
        this.title = requireNonNull(title);
        this.category = requireNonNull(category);
    }

    public MovieCategory getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }
}