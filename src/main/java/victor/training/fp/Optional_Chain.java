package victor.training.fp;

import lombok.Data;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class Optional_Chain {
   static MyMapper mapper = new MyMapper();

   public static void main(String[] args) {
      Parcel parcel = new Parcel();
//		parcel.setDelivery(new Delivery(new Address(new ContactPerson("John"))));
		parcel.setDelivery(new Delivery(new Address(null)));
//      parcel.setDelivery(new Delivery(null));

      DeliveryDto dto = mapper.convert(parcel);
      System.out.println(dto);
   }
}

class MyMapper {
   public DeliveryDto convert(Parcel parcel) {
      DeliveryDto dto = new DeliveryDto();
      dto.recipientPerson = parcel.getDelivery()
          .flatMap(d->d.getAddress().getContactPerson())
          .map(cp->cp.getName().toUpperCase())
          .orElse(null);
      return dto;
   }
}


@Data
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
      this.address = requireNonNull(address);
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = requireNonNull(address); // TODO null safe
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
