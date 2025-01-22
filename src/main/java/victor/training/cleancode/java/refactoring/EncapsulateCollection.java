package victor.training.cleancode.java.refactoring;

import java.util.ArrayList;
import java.util.List;

public class EncapsulateCollection {
    public static void main(String[] args) {
        HotelCharges hotelCharges = new HotelCharges();
        HotelDayCharge dayCharge = new HotelDayCharge(100, true, 5);

        hotelCharges.days.add(dayCharge);
        System.out.println("FEE: " + hotelCharges.totalFee + "\n");

        // Never forget to do:
        hotelCharges.computeTotal();
        System.out.println("FEE: " + hotelCharges.totalFee + "\n");

    }
}

class HotelCharges {
    public List<HotelDayCharge> days = new ArrayList<>();
    public double totalFee;

    public void computeTotal() {
        final double BREAKFAST_FEE = 10;
        final double PARKING_HOUR_RATE = 2;
        totalFee = 0;
        for (HotelDayCharge day : days) {
            totalFee += day.dayRate();
            if (day.breakfast()) {
                totalFee += BREAKFAST_FEE;
            }
            totalFee += day.parkingHours() * PARKING_HOUR_RATE;
        }
    }
}

record HotelDayCharge(double dayRate,
                      boolean breakfast,
                      int parkingHours) {
}