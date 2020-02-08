package victor.training.refactoring.inheritance;

class TheatreEvent {
    enum Category {
        TYPE_MUSICAL,
        TYPE_DRAMA,
        TYPE_COMEDY
    }

    private String title;
    private int duration;
    private int weekDay;
    private Category type;
    private boolean adultComedy;

    public String getTitle() {
        return title;
    }

    public int minimumAge() {
        switch (type) {
            case TYPE_MUSICAL:
                return 6;
            case TYPE_DRAMA:
                return 10;
            case TYPE_COMEDY:
                return adultComedy ? 18 : (duration > 120 ? 9 : 7);
        }
        return -1;
    }

    public boolean isWeekend() {
        return weekDay >= 6;
    }

    public float basePrice() {
        switch (type) {
            case TYPE_MUSICAL:
                return 60 + (isWeekend() ? 10 : 0);
            case TYPE_DRAMA:
                return 40 + (isWeekend() ? 5 : 0);
            case TYPE_COMEDY:
                return 50 - (adultComedy ? 5 : 0);
        }
        return 0;
    }
}