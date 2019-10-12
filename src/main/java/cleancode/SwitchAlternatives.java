package cleancode;

public class SwitchAlternatives {

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
    public double calculateFidelityPoints(int daysRented) {
        switch (type) {
            case CHILDREN: return daysRented / 2d;
            case REGULAR: return daysRented;
            case NEW_RELEASE: return daysRented + 1;
            default: throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}