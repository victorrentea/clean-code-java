package victor.training.refactoring;


class Cheese {
    private final int basePrice;
    private int discount;
//    private int discountedTotal; // regula ca discountedTotal + discount = basePrice

    public Cheese(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getDiscountedTotal() {
        return basePrice - discount;
    }

    public void discount(int discount) {
        this.discount = discount;
    }

    public static void main(String[] args) {
        Cheese cheese = new Cheese(10);
        System.out.println("New Cheese Price: " + cheese.getDiscountedTotal() + "\n");
        cheese.discount(1);
        System.out.println("Old Cheese Price: " + cheese.getDiscountedTotal() + "\n");

    }
}

