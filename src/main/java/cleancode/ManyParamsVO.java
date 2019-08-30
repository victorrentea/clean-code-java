package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        new ManyParamsVO().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
    }
    public void placeOrder(FullName fullName, Address address) {
        if (fullName == null) {
            throw new IllegalArgumentException();
        }


    }
}

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
        //distant logic
    }
}


class Person {

//    private String firstName;
//    private String lastName;
    private FullName fullName;
}