package videostore.horror;

public class SomewhereElseOverTheHillsAndFarAway {
    public int getMaxRentDays(Movie movie) {


        int v = switch (movie.getPriceCode()) { // the compiler checks you've covered all the branches
            // only works if the switch is used as an expression (returning values) and you switch on an enum
            case REGULAR -> 7;
            case NEW_RELEASE -> 2;
            case CHILDRENS -> 15;
            case ELDERS -> 0;
//            default -> System.out.println("BAD PRACTICE !");
        };
        return v;
    }

    public void handleMessage(String message) {
       switch (MessageTypeEnum.valueOf(String.valueOf(message.charAt(0)))) {
            case A -> fa();
            case B -> fb();
           default ->
                   throw new IllegalStateException("Unexpected value: " + MessageTypeEnum.valueOf(String.valueOf(message.charAt(0))));
       }
    }
    enum MessageTypeEnum {
        A, B ,C
    }

    private Object fa() {
//        throw new RuntimeException("Method not implemented");
        return null;
    }

    private void fb() {
        throw new RuntimeException("Method not implemented");
    }

}
