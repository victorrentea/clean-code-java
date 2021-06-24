package victor.training.refactoring;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncapsulateCollection {
    public static void main(String[] args) {
        HotelCharges hotelCharges = new HotelCharges();
        HotelDayCharge dayCharge = new HotelDayCharge(100, true, 5);

        hotelCharges.addDay(dayCharge);
        System.out.println("FEE: " + hotelCharges.getTotalFee() + "\n");

        // Never forget to do:
//        hotelCharges.computeTotal();
        System.out.println("FEE: " + hotelCharges.getTotalFee() + "\n");

    }
}

class HotelCharges {
    private List<HotelDayCharge> days = new ArrayList<>();
    private double totalFee;

    public void addDay(HotelDayCharge day) {
        days.add(day);
        computeTotal();
    }

    public double getTotalFee() {
        return totalFee;
    }

    public List<HotelDayCharge> getDays() {
        return Collections.unmodifiableList(days);
    }

    private void computeTotal() {
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
}

@Data
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
}