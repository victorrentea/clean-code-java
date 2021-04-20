package victor.training.refactoring;


class BadCheese {
    private int discountedTotal;
    private int discount = 0;

    public BadCheese(int basePrice) {
        this.discountedTotal = basePrice;
    }

    public int getDiscountedTotal() {
        return discountedTotal;
    }

    public void discount(int aNumber) {
        int old = this.discount;
        this.discount = aNumber;
        this.discountedTotal += old - aNumber;
    }
}
class Cheese {
    private final int basePrice;
    private int discount = 0;

    public Cheese(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getDiscountedTotal() { // synthetic getter;
        return basePrice - discount;
    }

    public void discount(int aNumber) {
        this.discount = aNumber;
    }

    public static void main(String[] args) {
        Cheese cheese = new Cheese(10);
        System.out.println("New Cheese Price: " + cheese.getDiscountedTotal() + "\n");
        cheese.discount(1);
        System.out.println("Old Cheese Price: " + cheese.getDiscountedTotal() + "\n");

    }
}

