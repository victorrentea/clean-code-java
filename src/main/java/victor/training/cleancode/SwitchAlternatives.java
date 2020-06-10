package victor.training.cleancode;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.List;
import java.util.function.Function;

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

class MoviePriceCalculationHelper {
    public static double randomBonus(int daysRented) {
    return -1;    }
}
@Service
class RegularMovieCalculations implements MovieCalculations {
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 2 + MoviePriceCalculationHelper.randomBonus(daysRented);
    }
//    double calculateFidelityPoints(int daysRented) {..}
//    double calculateMaxDays() {..}
}
@Service
class NewReleaseMovieCalculations implements MovieCalculations {
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3 + MoviePriceCalculationHelper.randomBonus(daysRented);
    }
//    double calculateFidelityPoints(int daysRented) {..}
//    double calculateMaxDays() {..}
}
@Service
class ChildrenMovieCalculations implements MovieCalculations {
    @Autowired// merge
    private OtherDependency other;
    @Override
    public double calculatePrice(int daysRented) {
        //tot nu pot accesa nicio dependinta injectata de spring/ejb/@Inject
        return daysRented + 1 + MoviePriceCalculationHelper.randomBonus(daysRented);
    }
//    double calculateFidelityPoints(int daysRented) {..}
//    double calculateMaxDays() {..}
}

// ----------------------------  daca ai mult cod comun intre implementarile unei functi specifice tipului:
@Service
class MoviePriceCalculations {
    @Autowired// merge
    private OtherDependency other;

    public static double calculateRegularPrice(int daysRented) {
        return daysRented * 2 + randomBonus(daysRented);
    }
    public static double calculateNewReleasePrice(int daysRented) {
        return daysRented * 3 + randomBonus(daysRented);
    }
    public static double calculateChildrenPrice(int daysRented) {
        //tot nu pot accesa nicio dependinta injectata de spring/ejb/@Inject
        return daysRented + 1 + randomBonus(daysRented);
    }
    private static double randomBonus(int daysRented) {
        return 0; // TODO
    }
}
// f(int):double

class Movie {
    private int x;
    enum Type {
        REGULAR(MoviePriceCalculations::calculateRegularPrice),
        NEW_RELEASE(MoviePriceCalculations::calculateNewReleasePrice),
        CHILDREN(MoviePriceCalculations::calculateChildrenPrice);

        public final Function<Integer, Double> calculations;

        Type(Function<Integer, Double> calculations) {
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
        return type.calculations.apply(daysRented);
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


