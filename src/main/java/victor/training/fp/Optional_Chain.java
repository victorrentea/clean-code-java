package victor.training.fp;

import lombok.NonNull;

import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

public class Optional_Chain {
	static MyMapper mapper = new MyMapper();
   public static void main(String[] args) {
		Parcel parcel = new Parcel();
//		parcel.setDelivery(new Delivery(new Address(new ContactPerson("John"))));
		parcel.setDelivery(new Delivery(null));

		DeliveryDto dto = mapper.convert(parcel);
      System.out.println(dto);
   }
}

class MyMapper {
   public DeliveryDto convert(Parcel parcel) {
      DeliveryDto dto = new DeliveryDto();

//      if (
//          parcel!=null &&
//          parcel.getDelivery()!=null &&
//          parcel.getDelivery().getAddress()!=null &&
//          parcel.getDelivery().getAddress().getContactPerson()!=null &&
//          parcel.getDelivery().getAddress().getContactPerson().getName()!=null
//      )
//         dto.recipientPerson = parcel.getDelivery().getAddress().getContactPerson().getName().toUpperCase();
      dto.recipientPerson = parcel.getDelivery()
          .flatMap(delivery -> delivery.getAddress().getContactPerson())
          .map(person ->person.getName().toUpperCase())
          .orElse(null); // safe sa pui null intr-un DTO de iesire,
      // caci nimeni altcineva nu se se va mai impiedica de acel NULL
      return dto;
   }
}

class DeliveryDto {
	public String recipientPerson;
}

// ENTITY
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
//   @Email
//   String email;
//   @NotNull // mai "slaba" pentru ca poti sa uiti sa validezi obiectul sau sa fie prea tarziu
   private Address address; // NOT NULL IN DB

   public Delivery(/*@NonNull */Address address) {
//      if (address == null) {
//         throw new IllegalArgumentException();
//      }
//      this.address = address;
      // hocus pocus
//      Validator v; v.validate(this);
      this.address = Objects.requireNonNull(address); // 💗
   }
   protected Delivery() {} // Oricine-l foloseste pe asta, va putea crea ob cu address=null

	public void setAddress(Address address) {
      this.address = Objects.requireNonNull(address); // TODO null safe
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
