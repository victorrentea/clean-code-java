package victor.training.refactoring;

import javax.persistence.Entity;
import java.util.Optional;

class NullObject {
    public void process(Customer customer) {
        String customerName;
        if (customer == null) {
            customerName = "occupant";
        } else {
            customerName = customer.getName();
        }
        // ...
    }

    // java 7 style
//    public void process2(Customer customer) {
//        String customerName;
//        if (customer.getName() == null) {
//            customerName = "occupant";
//        } else {
//            customerName = customer.getName();
//        }
//
//        customerName = StringUtils.defaultIfBlank(customer.getName(), "occupant");// bun.
//        System.out.println("SEnding to " + customerName);

    //  riscul este ca in alta clasa cineva sa nu observe ca name poate veni null
//    }
    // dar daca in alta clasa, altcineva face:
//    public void altaUseCase(Customer customer) {
//        System.out.println("Invoicing: "+ customer.getName().toUpperCase());
//        // ...
//    }

    //java 8 style
    public void process2(Customer customer) {
        // 1: apelantul observa va vine Optional<>, adica poate nimic
        // 2: decide el ce sa faca cu absenta (Optional.empty())
        String customerName = customer.getName().orElse("occupant");
        System.out.println("SEnding to " + customerName);
    }
    public void altaUseCase(Customer customer) {
        System.out.println("Invoicing: "+ customer.getName().map(String::toUpperCase).orElse("NO ONE"));
    }

}

@Entity
class Customer {
    private String name; // vreau sa ramana null in DB.
    private MemberCard memberCard;

    Customer(String name) {
        this.name = name;
    }

        // daca mereu app printeaza "occupant" cand name = null, atunci asta e cea mai buna solutie, dar as numi getNameSafe()
//    public String getName() {
//        if (name == null) {
//            return "occupant"; // e posibil ca anumite UC sa vrea alt string default
//        }
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
    public Optional<MemberCard> getMemberCard() {
        return Optional.ofNullable(memberCard);
    }
}

class PriceService {
    public int computePrice(Customer customer, Product product) {
        return customer.getMemberCard().map(card -> card.discountPrice(product)).orElse(product.getPrice());
        // presupunem ca faci des linia de mai sus
    }
}

//----------------
class MemberCard {
    private final int discountPercentage;

    MemberCard(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int discountPrice(Product product) {
        if (product.isRegular()) {
            return  product.getPrice() * (100-discountPercentage)/100;
        } else {
            return product.getPrice();
        }
    }
}

class Product {

    public boolean isRegular() {
        return false;
    }

    public int getPrice() {


    }
}
