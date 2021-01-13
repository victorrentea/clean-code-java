package victor.training.java8;

public class C__Optional_Chain {
	public static void main(String[] args) {
		MyMapper mapper = new MyMapper();
		DeliveryDto dto = mapper.convert(new Delivery(new Address(new ContactPerson("John"))));
		System.out.println(dto);
	}
}

class MyMapper {
	public DeliveryDto convert(Delivery entity) {
		DeliveryDto dto = new DeliveryDto();
		dto.setRecipientPerson(entity.getAddress().getContactPerson().getName().toUpperCase());
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
		this.address = address;
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

	public ContactPerson getContactPerson() {
		return contactPerson;
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
