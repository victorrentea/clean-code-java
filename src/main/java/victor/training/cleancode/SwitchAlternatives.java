package victor.training.cleancode;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.List;

import static java.util.Arrays.asList;

public class SwitchAlternatives {
    public static void main(String[] args) {
        List<Movie> movies = asList(
                new NewReleaseMovie("Star Wars"),
                new ChildrenMovie90("Sofia", 12),
                new RegularMovie90("Inception"));
        System.out.println(computeTotalPrice(movies));
    }
    public static double computeTotalPrice(List<Movie> movies) {
        return movies.stream().mapToDouble(m -> m.calculatePrice(3)).sum();
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

    public double calculateRegularPrice(int daysRented) {
        return daysRented * 2 + randomBonus(daysRented);
    }
    public double calculateNewReleasePrice(int daysRented) {
        return daysRented * 3 + randomBonus(daysRented);
    }
    public double calculateChildrenPrice(int daysRented) {
        //tot nu pot accesa nicio dependinta injectata de spring/ejb/@Inject
        return daysRented + 1 + randomBonus(daysRented);
    }
    private double randomBonus(int daysRented) {
        return 0; // TODO
    }
}
// f(int):double
////  ----- ai mUUUUULTA LOGICA (20-50 linii de cod) SAU campuri specifice doar unui subtip de film

class NewReleaseMovie extends Movie { // dezavantaj: un film new release va deveni regular peste 6 luni. cu mostenire nu poti schimba tipul
    public NewReleaseMovie(String name) {
        super(name);
    }
    public double calculatePrice(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public double calculateFidelityPoints(int daysRented) {
        return 0; // TODO
    }
}
abstract class ChildrenMovie extends Movie {
    private final int minAge; // avantaj: cand datele difera intre subtipuri
    public ChildrenMovie(String name, int minAge) {
        super(name);
        this.minAge = minAge;
    }
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented+ 1;
    }

    @Override
    public double calculateFidelityPoints(int daysRented) {
        return 0; // TODO
    }
}
class ChildrenMovie90 extends ChildrenMovie {
    public ChildrenMovie90(String name, int minAge) {
        super(name, minAge);
    }
}
abstract class RegularMovie extends Movie {
    public RegularMovie(String name) {
        super(name);
    }
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 2;
    }

    @Override
    public double calculateFidelityPoints(int daysRented) {
        return 0; // TODO
    }
}

class RegularMovie90 extends RegularMovie {
    public RegularMovie90(String name) {
        super(name);
    }
}
class RegularMovie80 extends RegularMovie {
    public RegularMovie80(String name) {
        super(name);
    }
}


abstract class Movie {
    private int x;
//    enum Type {
//        REGULAR(MoviePriceCalculations::calculateRegularPrice),
//        NEW_RELEASE(MoviePriceCalculations::calculateNewReleasePrice),
//        CHILDREN(MoviePriceCalculations::calculateChildrenPrice);
//
//        public final BiFunction<MoviePriceCalculations, Integer, Double> calculations;
//
//        Type(BiFunction<MoviePriceCalculations, Integer, Double> calculations) {
//            this.calculations = calculations;
//        }
//    }
//    private final Type type;
    private final String name;
//    private final int minAgeForChildren;
    public Movie(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
//    public double calculatePrice(int daysRented/* NU FA ASTA, ApplicationContext spring*/) {
////        switch (type) {
////            case CHILDREN: return daysRented + 1;
////            case REGULAR: return daysRented * 2;
////            case NEW_RELEASE: return daysRented * 3;
////            default: throw new IllegalStateException("Unexpected value: " + type);
////        }
////        MovieCalculations calculations = spring.getBean(type.calculations); -- va functiona in spring.
//        // TODO trebuie sa muti aceasta metoda in exteriorul entitatii, intr-o alta clasa manageuita de spring
//        MoviePriceCalculations implDiNSpring = new MoviePriceCalculations();
//        return type.calculations.apply(implDiNSpring, daysRented);
//    }
    public abstract double calculatePrice(int daysRented);

    // TODO see bellow other switches

    public abstract double calculateFidelityPoints(int daysRented);
//    {
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
}


