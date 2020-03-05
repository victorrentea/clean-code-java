package victor.training.cleancode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.math.BigDecimal;
import java.util.Optional;

public class ManyParamsVO {
    public static void main(String[] args) {
        FullName fullName = new FullName("John", "Doe");
        Address address = new Address("St. Albergue", "Paris", 99);
        new ManyParamsVO().placeOrder(fullName, address);
    }
    public void placeOrder(FullName fullName, Address address) {

    	System.out.println("Some Logic");
    }
}

@Embeddable
class FullName {
    private final String firstName;
    private final String lastName;
    FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String asEnterpriseName() {
        return firstName + " " + lastName.toUpperCase();
    }

    public FullName withLastName(String newLastName) {
        return new FullName(firstName, newLastName);
    }
}

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {

    	System.out.println("Another distant Logic");
    }
}

// Holy Entity
class Person {
    private Long id;
    private String phone;
    @Embedded
    private FullName fullName;

    public Person(String firstName, String lastName) {
        fullName = new FullName(firstName, lastName);
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void marita(Person person) {
        fullName = fullName.withLastName(person.getFullName().getLastName());
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }
}

class PersonService {
    public void f(Person person) {
        System.out.println(person.getFullName().asEnterpriseName());
    }

    public void p(Address address) {
        System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
    }
}