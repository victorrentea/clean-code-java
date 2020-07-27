package victor.training.cleancode;

public class ManyParamsVO {
	public static void main(String[] args) {
		PersonName personName = new PersonName("John", "Doe");
		Address address = new Address("St. Albergue", "Paris", 99);
		new ManyParamsVO().placeOrder(personName, address);
	}

	public void placeOrder(PersonName personName, Address address) {
		if (personName.getFirstName() == null || personName.getLastName() == null)
			throw new IllegalArgumentException();

		System.out.println("Some Logic");
		System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " "
				+ address.getStreetNumber());

	}
}

//class CreateOrderRequest { // too specific. Won't ever be used anywhere else except this method call -> probably = OVERENGINEERING

//class Person { // too general purpose. too broad. will explode 
//class Customer { // too general purpose. too broad. will explode 
// 30 more fields here.
//class Profile {	 // every freaking field can fit here
//class PersonalInformation {  // every freaking field can fit here
//}
//class OrderAddress {} // might be reused
//class Name // wrong because a company also has a name
//class PersonName {
//	private final String firstName;
//	private final String lastName;
//}
class Address {
	private final String city;
	private final String streetName;
	private final Integer streetNumber;

	public Address(String city, String streetName, Integer streetNumber) {
		this.city = city;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public String getStreetName() {
		return streetName;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

}

class AnotherClass {
	public void otherMethod(PersonName personName, int x) {
		if (personName.getFirstName() == null || personName.getLastName() == null)
			throw new IllegalArgumentException();

		System.out.println("Another distant Logic " + x);
		System.out.println("Person: " + personName.getLastName());
	}
}










// @Entity model
class Person {
	private Long id;
	private String phone;
	private PersonName name;

	public Person(String firstName, String lastName) {
		this.name = new PersonName(firstName, lastName);
		if (firstName == null || lastName == null)
			throw new IllegalArgumentException();
	}


	// TODO hard-core: implement setter
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

	public PersonName getName() {
		return name;
	}
}

class PersonService {
	public void f(Person person) {
		String fullNameStr = person.getName().getFullName();
		System.out.println(fullNameStr);
	}

	public void p(String city, String streetName, Integer streetNumber) {
		System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
	}
}