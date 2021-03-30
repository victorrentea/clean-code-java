package victor.training.cleancode;

import java.util.List;

import static java.util.Arrays.asList;

public class SwitchAlternatives {
    public static void main(String[] args) {
        List<Movie> movies = asList(
                new Movie(Movie.Type.NEW_RELEASE, "Star Wars"),
                new Movie(Movie.Type.CHILDREN, "Sofia"),
                new Movie(Movie.Type.REGULAR, "Inception"));
        System.out.println(computeTotalPrice(movies));
//        movies = null;
    }
    public static double computeTotalPrice(List<Movie> movies) {
        return movies.stream().mapToDouble(m -> m.calculatePrice(3)).sum();
    }
}

class RegularMovie  extends Movie {
    public RegularMovie(Type type, String name) {
        super(type, name);
    }
}
class NewReleaseMovie  extends Movie {
    public NewReleaseMovie(Type type, String name) {
        super(type, name);
    }
}
class Movie {
    enum Type {
        REGULAR, NEW_RELEASE, CHILDREN
    }
    private final Type type;
    private final String name;
    public Movie(Type type, String name) {
        this.type = type;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public double calculatePrice(int daysRented) {
        switch (type) {
            case CHILDREN: return daysRented + 1;
            case REGULAR: return daysRented * 2;
            case NEW_RELEASE: return daysRented * 3;
            default: throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    // TODO see bellow other switches

}


//    public double calculateFidelityPoints(int daysRented) {
//        switch (type) {
//            case CHILDREN: return daysRented / 2d;
//            case REGULAR: return daysRented;
//            case NEW_RELEASE: return daysRented + 1;
//            default: throw new IllegalStateException("Unexpected value: " + type);
//        }
//    }
//    public int maxRentalDays() {
//        switch (type) {
//            case CHILDREN: return 5;
//            case REGULAR: return 4;
//            case NEW_RELEASE: return 3;
//            default: throw new IllegalStateException("Unexpected value: " + type);
//        }
//    }
