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

    public String getName() {
        return "blah";
    }
}
