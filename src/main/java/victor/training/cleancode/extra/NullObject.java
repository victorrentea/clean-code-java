package victor.training.cleancode.extra;

class NullObject {
    public void process(Customer customer) {
        String customerName;
        if (customer == null) {
        } else {
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
