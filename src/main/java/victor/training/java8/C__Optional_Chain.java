package victor.training.java8;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class C__Optional_Chain {
	public static void main(String[] args) {
		MyMapper mapper = new MyMapper();
//		DeliveryDto dto = mapper.convert(new Delivery(new Address(new ContactPerson("John"))));
		 mapper.convert(new Delivery(new Address(null)));
		DeliveryDto dto = mapper.convert(new Delivery(null));
		System.out.println(dto);
	}
}

class MyMapper {
	public DeliveryDto convert(Delivery entity) {
		DeliveryDto dto = new DeliveryDto();
		dto.setRecipientPerson(entity.getAddress()
			.getContactPerson()
			.map(ContactPerson::getName)
			.map(String::toUpperCase).orElse(""));
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


//@Access(AccessType.PROPERTY)
@AllArgsConstructor
class Delivery {
	@Setter
	@NonNull
//	@Basic(optional = false)
//	@NotNull
	private Address address; // NOT NULL IN DB

//	public Delivery(Address address) {
//		setAddress(address);
//	}

//	public void setAddress(Address address) {
//		this.address = requireNonNull(address);
//	}

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
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
