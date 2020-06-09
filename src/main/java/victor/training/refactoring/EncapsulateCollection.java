package victor.training.refactoring;

import java.util.ArrayList;
import java.util.List;

public class EncapsulateCollection {
    public static void main(String[] args) {
        HotelCharges hotelCharges = new HotelCharges();
        HotelDayCharge dayCharge = new HotelDayCharge(100, true, 5);

//        hotelCharges.getDays().add(dayCharge); -- nu ai sansa sa fii destept
        hotelCharges.addDay(dayCharge);
        System.out.println("FEE: " + hotelCharges.totalFee + "\n");

//        hotelCharges.setDays(Arrays.asList(dayCharge));

        // Never forget to do:
//        hotelCharges.computeTotal(); // redundant. Implem ta actual are grija sa faca ere asta
        System.out.println("FEE: " + hotelCharges.totalFee + "\n");

    }
}

class HotelCharges {
    private List<HotelDayCharge> days = new ArrayList<>();
    public double totalFee;

    public void computeTotal() {
        final double BREAKFAST_FEE = 10;
        final double PARKING_HOUR_RATE = 2;
        totalFee = 0;
        for (HotelDayCharge day : days) {
            totalFee += day.getDayRate();
            if (day.isBreakfast()) {
                totalFee += BREAKFAST_FEE;
            }
            totalFee += day.getParkingHours() * PARKING_HOUR_RATE;
        }
    }

    public Iterable<HotelDayCharge> getDays() {
        return days;
    }

    public HotelCharges addDay(HotelDayCharge/*Idea: ... varargs */dayCharge) {
        days.add(dayCharge);
        computeTotal();
        // daca te joci cu JPA/Hibernate, aici poti seta relatii bidirectionale
        return this;
    }
    public void removeDay(HotelDayCharge dayCharge) {
        days.remove(dayCharge);
        computeTotal();
    }
//
//    public HotelCharges setDays(List<HotelDayCharge> days) {
//        this.days = days;
//        return this;
//    }
}

class HotelDayCharge {
    private double dayRate;
    private boolean breakfast;
    private int parkingHours;
    private HotelCharges hotel;

    public HotelDayCharge(double dayRate, boolean breakfast, int parkingHours) {
        this.dayRate = dayRate;
        this.breakfast = breakfast;
        this.parkingHours = parkingHours;
    }

    public double getDayRate() {
        return dayRate;
    }

    public HotelCharges getHotel() {
        return hotel;
    }

    public int getParkingHours() {
        return parkingHours;
    }

    public boolean isBreakfast() {
        return breakfast;
    }
}