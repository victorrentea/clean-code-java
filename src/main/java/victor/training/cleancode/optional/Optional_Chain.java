package victor.training.cleancode.optional;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class Optional_Chain {
  private static final MyMapper mapper = new MyMapper();

  public static void main(String[] args) {
    Parcel parcel = new Parcel();
    parcel.setDelivery(new Delivery(new Address(null)));
    parcel.setDelivery(new Delivery(null));

    DeliveryDto dto = mapper.convert(parcel);
    System.out.println(dto);
  }
}

class MyMapper {
   public DeliveryDto convert(Parcel parcel) {
     DeliveryDto dto = new DeliveryDto();
     //      if ( // terror , your data model is your enemy here.
     //              parcel != null &&
     //              parcel.getDelivery() != null &&
     //              parcel.getDelivery().getAddress() != null &&
     //              parcel.getDelivery().getAddress().getContactPerson() != null &&
     //              parcel.getDelivery().getAddress().getContactPerson().getName() != null
     //      )
     dto.recipientPerson = parcel.getDelivery()
             .flatMap(d -> d.getAddress().getContactPerson())
             .map(cp -> cp.getName().toUpperCase())
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
}


class Delivery {
  @NotNull
  private Address address; // NOT NULL IN DB

   public Delivery(Address address) {
     this.address = requireNonNull(address);
   }

	public void setAddress(Address address) {
      this.address = requireNonNull(address); // TODO null safe
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
     this.name = requireNonNull(name);
   }

   public String getName() {
      return name;
   }
}
