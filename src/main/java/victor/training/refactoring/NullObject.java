package victor.training.refactoring;

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

}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
