package videostore.horror;

import java.util.function.BiFunction;
import java.util.function.Function;

public record Movie(String title, PriceCode priceCode) {

    enum PriceCode {
        CHILDREN/*{
            @Override
            public double calculatePrice(int daysRented) {
                double result = 1.5;
                if (daysRented > 3)
                    result += (daysRented - 3) * 1.5;
                return result;
            }
        }*/,
        REGULAR /*{
            @Override
            public double calculatePrice(int daysRented) {
                double result = 2;
                if (daysRented > 2)
                    result += (daysRented - 2) * 1.5;
                return result;
            }
        }*/,
        NEW_RELEASE /*{
            @Override
            public double calculatePrice(int daysRented) {
                return daysRented * 3;
            }
        }*/,
        ELDERS
        ;

//        public abstract double calculatePrice(int daysRented);
    }
    //vezi si https://dzone.com/articles/functional-programming-patterns-with-java-8

}