package victor.training.fp;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Optional;

public class Optional_Chain {
    private static final MyMapper mapper = new MyMapper();

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
//        if (
//                parcel != null &&
//                parcel.getDelivery() != null &&
//                parcel.getDelivery().getAddress() != null &&
//                parcel.getDelivery().getAddress().getContactPerson() != null &&
//                parcel.getDelivery().getAddress().getContactPerson().getName() != null
//        )
//            dto.recipientPerson = parcel.getDelivery().getAddress().getContactPerson().getName().toUpperCase();

//        if (parcel.getDelivery().isPresent()) {
//            side effects
//        }
//        parcel.getDelivery().or(d->sendNotificationPeRetea=SideEffect);

        dto.recipientPerson = parcel.getDelivery()
                .flatMap(delivery -> delivery.getAddress().getContactPerson())
                .map(cp -> cp.getName().toUpperCase())
                .orElse(null);

        parcel.getDelivery()
                .flatMap(delivery -> delivery.getAddress().getContactPerson())
                .map(cp -> cp.getName().toUpperCase())
                .ifPresent(nume -> dto.recipientPerson = nume);

        dto.missingPersonReason = determineReason(parcel.getDelivery());
        return dto;
    }

    private String determineReason(Optional<Delivery> delivery) {
        if (delivery.isEmpty()) return "DELIVERY_NOT_SCHEDULED";
        if (delivery.get().getAddress().getContactPerson() == null) return "DELIVERING TO COMPANY";
        return null;
    }
}

class DeliveryDto {
    public String recipientPerson;
    public String missingPersonReason;
}

//@Entity
class Parcel { // intre cele sfinte MODELUL
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
        setAddress(address);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = Objects.requireNonNull(address); // TODO null safe
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

class AltaParte {
    public void method(Customer c) {
        System.out.println(c.getMemberCard().toUpperCase());
    }
}

class Customer {
    @Nullable
    String getMemberCard() {
        return null;
    }
}