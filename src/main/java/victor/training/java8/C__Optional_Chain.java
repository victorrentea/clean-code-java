package victor.training.java8;

import java.util.Objects;
import java.util.Optional;

public class C__Optional_Chain {
	public static void main(String[] args) {
		MyMapper mapper = new MyMapper();
		DeliveryDto dto = mapper.convert(new Delivery(new Address(new ContactPerson("John"))));
		System.out.println(dto);
	}
}

class MyMapper {
	public DeliveryDto convert(Delivery delivery) {
		DeliveryDto dto = new DeliveryDto();
//		if (
//			entity.getAddress()!= null &&
//			entity.getAddress().getContactPerson()!= null &&
//			entity.getAddress().getContactPerson().getName()!= null
//			 )
		dto.setRecipientPerson(delivery.getAddress().getContactPerson()
			.map(person-> person.getName().toUpperCase())
			.orElse(""));
		return dto;
	}
}

class DeliveryDto {
	private String recipientPerson;

	public String getRecipientPerson() {
		return recipientPerson;
	}

	public void setRecipientPerson(String recipientPerson) {
		this.recipientPerson = recipientPerson;
	}
}


class Delivery {
	private final Address address; // NOT NULL IN DB

	public Delivery(Address address) {
		this.address = Objects.requireNonNull(address);
	}

	public Address getAddress() {
		return address;
	}
}
class Address {
	private final ContactPerson contactPerson; // can be null

	public Address(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Optional<ContactPerson> getContactPerson() {
		return Optional.ofNullable(contactPerson);
	}
}

class ContactPerson {
	private final String name; // NOT NULL

	public ContactPerson(String name) {
		this.name = Objects.requireNonNull(name);
	}

	public String getName() {
		return name;
	}
}
