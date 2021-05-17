package victor.training.java8;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

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
      if (parcel == null) {
         return dto;
      }
//      if (
//          parcel.getDelivery() != null &&
//          parcel.getDelivery().getAddress() != null &&
//          parcel.getDelivery().getAddress().getContactPerson() != null &&
//          parcel.getDelivery().getAddress().getContactPerson().getName() != null &&
//          parcel.getDelivery().getAddress().getContactPerson().getName().toUpperCase(Locale.ROOT) != null
//      )
      dto.recipientPerson = parcel.getDelivery()

          .flatMap(delivery -> delivery.getAddress().getContactPerson())

//          .map(Delivery::getAddress)
//          .flatMap(Address::getContactPerson)

          .map(p -> p.getName().toUpperCase())
          .orElse("N/A");
      return dto;
   }
}

class DeliveryDto {
	public String recipientPerson;
}
class Parcel {
   private Delivery delivery; // NULL until a delivery is scheduled

   public Optional<Delivery> getDelivery() {
      return Optional.ofNullable(delivery);
   }
	public void setDelivery(Delivery delivery) {
      this.delivery = delivery;
   }
}


class Delivery {
   private Address address; // NOT NULL IN DB

   public Delivery(Address address) {
      this.address = Objects.requireNonNull(address);
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
