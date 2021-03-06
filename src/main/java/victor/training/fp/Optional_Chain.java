package victor.training.fp;

public class Optional_Chain {
	static MyMapper mapper = new MyMapper();
   public static void main(String[] args) {
		Parcel parcel = new Parcel();
		parcel.setDelivery(new Delivery(new Address(new ContactPerson("John"))));

		DeliveryDto dto = mapper.convert(parcel);
      System.out.println(dto);
   }
}

class MyMapper {
   public DeliveryDto convert(Parcel parcel) {
      DeliveryDto dto = new DeliveryDto();
      dto.recipientPerson = parcel.getDelivery().getAddress().getContactPerson().getName().toUpperCase();
      return dto;
   }
}

class DeliveryDto {
	public String recipientPerson;
}
class Parcel {
   private Delivery delivery; // NULL until a delivery is scheduled

   public Delivery getDelivery() {
      return delivery;
   }
	public void setDelivery(Delivery delivery) {
      this.delivery = delivery;
   }
}


class Delivery {
   private Address address; // NOT NULL IN DB

   public Delivery(Address address) {
      this.address = address;
   }

	public void setAddress(Address address) {
		this.address = address; // TODO null safe
	}

	public Address getAddress() {
      return address;
   }
}

class Address {
   private final ContactPerson contactPerson; // can be null

   public Address(ContactPerson contactPerson) {
      this.contactPerson = contactPerson;
   } // TODO allow not setting

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
