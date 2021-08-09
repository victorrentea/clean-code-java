package victor.training.fp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class Optional_Chain {
	static MyMapper mapper = new MyMapper();
   public static void main(String[] args) {
		Parcel parcel = new Parcel();
		parcel.setDelivery(new Delivery(new Address(null)));

		DeliveryDto dto = mapper.convert(parcel);
      System.out.println(dto);
   }
}

class MyMapper {
   public DeliveryDto convert(Parcel parcel) {
      DeliveryDto dto = new DeliveryDto();
      dto.recipientPerson = parcel.getRecipientPerson()
          .map(String::toUpperCase)
          .orElse(null);

      return dto;
   }

}

class DeliveryDto {
	public String recipientPerson;
}
class Parcel {
   private Delivery delivery; // NULL until a delivery is scheduled

   public Optional<Delivery> getDelivery() {
      return ofNullable(delivery);
   }
	public void setDelivery(Delivery delivery) {
      this.delivery = delivery;
   }

   public Optional<String> getRecipientPerson() {
      return getDelivery()
          .flatMap(d -> d.getAddress().getContactPerson())
          .map(ContactPerson::getName);
   }
}


@Data
@AllArgsConstructor
class Delivery {
   @NonNull
   private Address address; // NOT NULL IN DB

//   public Delivery(Address address) {
//      setAddress(address);
//   }

//	public void setAddress(Address address) {
//      this.address = requireNonNull(address); // TODO null safe
//	}

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
      this.name = requireNonNull(name);
   }

   public String getName() {
      return name;
   }
}
