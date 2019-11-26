package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        PersonName personName = new PersonName("John", "Doe");
        new ManyParamsVO().placeOrder(personName, "St. Albergue", "Paris", 99);
    }
    public void placeOrder(PersonName name, String city, String streetName, Integer streetNumber) {
        if (name == null) throw new IllegalArgumentException();
    	System.out.println("Some Logic");
    }
}
//@Embeddable -- JPA
class PersonName {
    private final String firstName;
    private final String lastName;
//    protected PersonName() {} // e un rau necesar, cerut de frameworkuri

    public PersonName(String firstName, String lastName) {
    	if (firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String asString() {
        return firstName + " " + lastName.toUpperCase();
    }
}

class AnotherClass {
    public void otherMethod(PersonName name, int x) {
    	if (name == null) throw new IllegalArgumentException();
    	
    	System.out.println("Another distant Logic");
    }
}

// sacrele entitati
class Person {
    private PersonName name;
    //mai sunt chestii aicea. alte campuri. CNP... .ETC

    public Person(PersonName name) {
        this.name = name;
        // TODO think: is this sufficient enforcing ?
    }

    public PersonName getName() {
        return name;
    }

}


class PersonService {
    public void f(Person person) {
        System.out.println(person.getName().asString());
    }

}