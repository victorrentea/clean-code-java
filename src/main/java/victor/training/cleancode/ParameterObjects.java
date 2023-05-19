package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class ParameterObjects {
  public static void main(String[] args) {
    new ParameterObjects().placeOrder(
            "John", "Doe",
            "St. Albergue", "Paris", 99);

    new AnotherClass().otherMethod("John", "Doe", 17);
  }

  public void placeOrder(String fName, String lName, String city, String streetName, Integer streetNumber) {
    if (fName == null || lName == null) throw new IllegalArgumentException();

    System.out.println("Some Logic");
    System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

  }
}

class AnotherClass {
  public void otherMethod(String firstName, String lastName, int x) {
    if (firstName == null || lastName == null) throw new IllegalArgumentException();

    System.out.println("Another distant Logic " + x);
    System.out.println("Person: " + lastName);
  }
}

// Domain Entity
class Person {
  private Long id;
  private String firstName;
  private String lastName;
  private List<String> phone = new ArrayList<>();

  public Person(String firstName, String lastName) {
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

  // TODO hard-core: implement setter
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}

class PersonService {
  public void f(Person person) {
    System.out.println("Hi there, " + person.getFirstName());

    String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
    System.out.println("Record for " + fullNameStr);
  }

  public void p(String streetName, String city, Integer streetNumber) {
    System.out.println("Living in " + city + " on street: " + streetName + " " + streetNumber);
  }

  public void pcaller() {
    p("Champs Elysees", "Paris", 91);
  }
}