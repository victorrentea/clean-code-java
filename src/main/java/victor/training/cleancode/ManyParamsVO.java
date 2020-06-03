package victor.training.cleancode;

import lombok.Data;
import lombok.Value;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

public class ManyParamsVO {
    public static void main(String[] args) {
        FullName fullName = new FullName("John", "Doe");
        new ManyParamsVO().placeOrder(fullName, "St. Albergue", "Paris", 99);
    }
    public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {

    	System.out.println("Some Logic");
    }
}

//class ClientName { // too specific, not reusable
//class ContactPerson { neah
//class Name { // is vague. a company also has name
//class PersonIdentifier {// not true, CNP, SSN
@Data
@Embeddable // best underused feature of JPA/Hivernate
class FullName {
    private String firstName;
    private String lastName;
    protected FullName() {}

    public FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

//class Address


class AnotherClass {
    {
        otherMethod("F","L",1);
    }
    public void otherMethod(FullName fullName, int x) {
        otherMethod(fullName.getFirstName(), fullName.getLastName(), x);


    }
    // TODO: Hunt down usages of this (CTRL-ALT-H) and point to the above function. at the end, inline the below func.
    public void otherMethod(String firstName, String lastName, int x) {
    	if (StringUtils.isNotEmpty(firstName)) {
          System.out.println(firstName.toUpperCase());
      }
    	System.out.println("Another distant Logic");
    }
}















// stinks like @Entity
// the sacred grounds of the persisten model
class Person {
    private Long id;
    @Embedded
    private FullName fullName;
    private String phone;

    public Person(String firstName, String lastName) {
        // TODO think: is this sufficient enforcing ?
        this.fullName = new FullName(firstName, lastName);
    }

    // TODO hard-core: implement setter when she marries him
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public FullName getFullName() {
        return fullName;
    }

}

class PersonService {
    public void f(Person person) {
        String fullNameStr = person.getFullName().getFirstName() + " " + person.getFullName().getLastName().toUpperCase();
        System.out.println(person.getFullName().getFirstName());
        System.out.println(fullNameStr);
    }
    public void p(String city, String streetName, Integer streetNumber) {
        System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
    }
}