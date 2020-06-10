package victor.training.cleancode;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.List;

import static java.util.Arrays.asList;

public class SwitchAlternatives {
    public static void main(String[] args) {
        List<Movie> movies = asList(
                new Movie(Movie.Type.NEW_RELEASE, "Star Wars"),
                new Movie(Movie.Type.CHILDREN, "Sofia"),
                new Movie(Movie.Type.REGULAR, "Inception"));
        System.out.println(computeTotalPrice(movies));
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


interface MovieCalculations {
    double calculatePrice(int daysRented);
//    double calculateFidelityPoints(int daysRented);
//    double calculateMaxDays();
}

@Service
class RegularMovieCalculations implements MovieCalculations {
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 2;
    }
}
@Service
class NewReleaseMovieCalculations implements MovieCalculations {
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3;
    }
}
@Service
class ChildrenMovieCalculations implements MovieCalculations {
    @Autowired// merge
    private OtherDependency other;
    @Override
    public double calculatePrice(int daysRented) {
        //tot nu pot accesa nicio dependinta injectata de spring/ejb/@Inject
        return daysRented + 1;
    }
}

class Movie {
    private int x;
    enum Type {
        REGULAR(RegularMovieCalculations.class),
        NEW_RELEASE(NewReleaseMovieCalculations.class),
        CHILDREN(ChildrenMovieCalculations.class);

        public final Class<? extends MovieCalculations> calculations;

        Type(Class<? extends MovieCalculations> calculations) {
            this.calculations = calculations;
        }
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
    public double calculatePrice(int daysRented/* NU FA ASTA, ApplicationContext spring*/) {
//        switch (type) {
//            case CHILDREN: return daysRented + 1;
//            case REGULAR: return daysRented * 2;
//            case NEW_RELEASE: return daysRented * 3;
//            default: throw new IllegalStateException("Unexpected value: " + type);
//        }
//        MovieCalculations calculations = spring.getBean(type.calculations); -- va functiona in spring.
        // TODO trebuie sa muti aceasta metoda in exteriorul entitatii, intr-o alta clasa manageuita de spring
        return calculations.calculatePrice(daysRented);
    }

    // TODO see bellow other switches

    public double calculateFidelityPoints(int daysRented) {
        switch (type) {
            case CHILDREN: return daysRented / 2d;
            case REGULAR: return daysRented;
            case NEW_RELEASE: return daysRented + 1;
            default: throw new IllegalStateException("Unexpected value: " + type);
        }
    }
    public int maxRentalDays() {
        switch (type) {
            case CHILDREN: return 5;
            case REGULAR: return 4;
            case NEW_RELEASE: return 3;
            default: throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}


