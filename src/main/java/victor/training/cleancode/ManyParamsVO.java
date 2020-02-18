package victor.training.cleancode;

import javax.persistence.Embedded;

public class ManyParamsVO {
    public static void main(String[] args) {
        Address address = new Address("St. Albergue", "Paris", 99);
        FullName fullName = new FullName("John", "Doe");
        new ManyParamsVO().placeOrder(fullName, address);
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


// Sacra Entitate
class Person {
    private Long id;
    @Embedded
    private FullName fullName;
    private String phone;

    public Person(String firstName, String lastName) {
        // TODO think: is this sufficient enforcing ?

        fullName = new FullName(firstName, lastName);
    }

    public void marita(Person sotzu) {
        fullName = fullName.withLastName(sotzu.getFullName().getLastName());
    }

    public FullName getFullName() {
        return fullName;
    }
}

class PersonService {
    public void f(Person person) {
        String fullName = person.getFullName().asEnterpriseName();
        System.out.println(fullName);
    }

    public void p(String city, String streetName, Integer streetNumber) {
        System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
    }
}