package victor.training.refactoring.inheritance;

class MusicalEvent extends TheatreEvent {
    public int minimumAge() {
        return 6;
    }
    public float basePrice() {
        return 60 + (isWeekend() ? 10 : 0);
    }
}
class DramaEvent extends TheatreEvent {
    public int minimumAge() {
        return 10;
    }
    public float basePrice() {
        return 40 + (isWeekend() ? 5 : 0);
    }
}
class ComedyEvent extends TheatreEvent {
    protected boolean adultComedy;

    public int minimumAge() {
        return adultComedy ? 18 : (duration > 120 ? 9 : 7);
    }
    public float basePrice() {
        return 50 - (adultComedy ? 5 : 0);
    }
}

abstract class TheatreEvent {
    enum Category {
        TYPE_MUSICAL,
        TYPE_DRAMA,
        TYPE_COMEDY
    }

    private String title;
    protected int duration;
    private int weekDay;
    private Category type;

    public String getTitle() {
        return title;
    }

    public abstract int minimumAge();

    public boolean isWeekend() {
        return weekDay >= 6;
    }

    public abstract float basePrice();

}