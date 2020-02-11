package victor.training.refactoring.incubator;

class Order
{
    private Warehouse warehouse;

    // TODO if delegate != null -> delegate.prioritityPlan...
    public int daysToShip() {
        return warehouse.getDaysToShip();
    }
}

class PriorityOrder extends Order
{
    private PriorityPlan priorityPlan;

    public int daysToShip() {
        return priorityPlan.getDaysToShip();
    }
}

class PriorityPlan {
    public int getDaysToShip() {
        return 3;
    }
}

class Warehouse {

    public int getDaysToShip() {
        return 5;
    }
}