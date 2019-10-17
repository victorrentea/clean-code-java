package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        Address address = new Address("St. Albergue", "Paris", 99);
        FullName fullName = new FullName("John", "Doe");
        new ManyParamsVO().placeOrder(fullName, address);
    }
    public void placeOrder(FullName fullName, Address address) {
        if (fullName == null) { throw new IllegalArgumentException("N-am incredere"); }
    	System.out.println("Some Logic");
    }
}

class FullName {
    private final String firstName;
    private final String lastName;
    public FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}


class Address {
    private final String city;
    private final String streetName;
    private final int streetNumber;

    Address(String city, String streetName, int streetNumber) {
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

}


// peste mari si tari

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
    	if (fullName == null) {
            throw new IllegalArgumentException();
        }
    	
    	System.out.println("Another distant Logic");
    }
}

//cele sfinte Entitate!!
class Person {

    static {
        //cod
        Person p = new Person(new FullName("j","d"));
        System.out.println(p.getFullName().getFirstName());
        System.out.println(p.getFullName().getFirstName());
        System.out.println(p.getFullName().getFirstName());
        System.out.println(p.getFullName().getFirstName());
        System.out.println(p.getFullName().getFirstName());
    }
//    @Embedded
    private FullName fullName;

    public Person(FullName fullName) {
        this.fullName = fullName;
        if (fullName == null) throw new IllegalArgumentException();
        // TODO think: is this sufficient enforcing ?
    }

    public FullName getFullName() {
        return fullName;
    }

//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
}