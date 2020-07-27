package victor.training.cleancode;

public class PersonName {
	private final String firstName;
	private final String lastName;

	public PersonName(String firstName, String lastName) {
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

	public String getFullName() {
		return firstName + " " + lastName.toUpperCase();
	}

	public PersonName withLastName(String newLastName) {
		return new PersonName(firstName, newLastName);
	}
}