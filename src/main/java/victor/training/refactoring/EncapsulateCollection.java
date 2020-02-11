package victor.training.refactoring;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EncapsulateCollection {
    public static void main(String[] args) {
        HotelCharges hotelCharges = new HotelCharges();
        HotelDayCharge dayCharge = new HotelDayCharge(100, true, 5);

//        hotelCharges.setDays(Arrays.asList(dayCharge));
        System.out.println("FEE: " + hotelCharges.getTotalFee() + "\n");

        hotelCharges.addDayCharge(dayCharge);
        System.out.println("FEE: " + hotelCharges.getTotalFee() + "\n");

        hotelCharges.computeTotal();
        System.out.println("FEE: " + hotelCharges.getTotalFee() + "\n");

    }
}

class HotelCharges {
    public static final double BREAKFAST_FEE = 10;
    public static final double PARKING_HOUR_RATE = 2;

    private List<HotelDayCharge> days = new ArrayList<>();
    private double totalFee;

    public void computeTotal() {
        totalFee = 0;
        for (HotelDayCharge day : days) {
            totalFee += day.getDayRate();
            if (day.isBreakfast()) {
                totalFee += BREAKFAST_FEE;
            }
            totalFee += day.getParkingHours() * PARKING_HOUR_RATE;

        }
    }

    public List<HotelDayCharge> getDays() {
        return Collections.unmodifiableList(days);
    }

//    public void setDays(List<HotelDayCharge> days) {
//        this.days = days;
//    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public void addDayCharge(HotelDayCharge dayCharge) {
        days.add(dayCharge);
        dayCharge.setHotel(this);
        computeTotal();
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