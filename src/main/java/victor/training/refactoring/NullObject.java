package victor.training.refactoring;

class NullObject {
    public static void main(String[] args) {
        new NullObject().process(Customer.NULL_CUSTOMER);
    }
    public void process(ICustomer customer) {
        String customerName;
//        if (customer == null) {
//            customerName = "occupant";
//        } else {
            customerName = customer.getName();
//        }
        if (customer.isActive()) {
            System.out.printf("ALOA");
        }
        // ...
    }

}

// Hard-core version: re-implementare cu logica alternativa
class NullCustomer implements ICustomer {

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public String getName() {
        return "occupant";
    }
}

class Customer implements ICustomer {
    // LIGHT version: date dummy intr-o constanta
    public static final ICustomer NULL_CUSTOMER = new Customer("occupant");

    private final String name;

    Customer(String name) {
        this.name = name;
    }

    @Override
    public boolean isActive() {
        // logica multa
        return true;
    }

    @Override
    public String getName() {
        return name;
    }
}
