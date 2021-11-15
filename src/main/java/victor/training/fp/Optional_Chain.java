package victor.training.fp;

import lombok.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

public class Optional_Chain {
   static MyMapper mapper = new MyMapper();

   public static void main(String[] args) {
      Parcel parcel = new Parcel();
      parcel.setDelivery(new Delivery(null));

      DeliveryDto dto = mapper.convert(parcel);
      System.out.println(dto);
   }
}

class MyMapper {
   public DeliveryDto convert(Parcel parcel) {
      DeliveryDto dto = new DeliveryDto();
//      if ( // NULL PYRAMID
//          parcel != null &&
//          parcel.getDelivery() != null &&
//          parcel.getDelivery().getAddress() != null &&
//          parcel.getDelivery().getAddress().getContactPerson() != null &&
//          parcel.getDelivery().getAddress().getContactPerson().getName() != null
//      ) {
//      dto.recipientPerson = parcel.getDelivery()
//          .getAddress().getContactPerson().getName().toUpperCase();

      // BAD because it relies of ifPresent(sideEffects)
//      parcel.getDelivery()
//          .ifPresent(delivery -> delivery.getAddress().getContactPerson()
//              .ifPresent(person -> dto.recipientPerson = person.getName()));

      dto.recipientPerson = parcel.getDelivery()
          // PRO: less chained operators
//          .flatMap(delivery -> delivery.getAddress().getContactPerson())
//          .map(person -> person.getName().toUpperCase())


         // PRO: more ::, "more FP"
          .map(Delivery::getAddress)
          .flatMap(Address::getContactPerson)
          .map(ContactPerson::getName)
          .map(String::toUpperCase)


          .orElse(null);

      String s = new Address(new ContactPerson("a")).getContactPersonRaw().toString();
      System.out.println(s);
//      
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
   private final Address address; // NOT NULL IN DB

   public Delivery(Address address) {
      this.address = Objects.requireNonNull(address);
   }

   public Address getAddress() {
      return address;
   }
}

class Address {
   @Nullable
   @NotNull // Validator.validate(address) javax.validation >>>
   private final ContactPerson contactPerson; // can be null

   public Address( @Nonnull ContactPerson contactPerson) {
      this.contactPerson = contactPerson;
   } // TODO allow not setting

   @Nullable
//   @Nonnull
   public ContactPerson getContactPersonRaw() {
      return contactPerson;
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
