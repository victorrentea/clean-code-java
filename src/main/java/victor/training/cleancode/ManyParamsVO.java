package victor.training.cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        new ManyParamsVO().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
    }
    public void placeOrder(FullName fullName, Address address) {
    	System.out.println("Some Logic");
    }
}

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {

    	System.out.println("Another distant Logic");
}
}

// ENTITATE FRATE.
class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private FullName fullName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        fullName = new FullName(firstName, lastName);
        // TODO think: is this sufficient enforcing ?
    }

    //    HARD CORE : public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public FullName getFullName() {
        return fullName;
    }

}

class PersonService {
    public void f(Person person) {
        System.out.println(person.getFullName().toNiceString());
    }

    public void p(String city, String streetName, Integer streetNumber) {
        System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
    }
}