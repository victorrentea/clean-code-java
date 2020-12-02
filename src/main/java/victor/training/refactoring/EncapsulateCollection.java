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
        m(hotelCharges, false);

//        hotelCharges.getDays().add(dayCharge);

//         hotelCharges.getDays().stream().filter(dc -> dc.isBreakfast()).anyMatch();

        for (HotelDayCharge day : hotelCharges.getDays()) {
            System.out.println("Day charge " + day);
        }
    }

    private static void m(HotelCharges hotelCharges, boolean ceva) {
        if (ceva){ altaFunctie(hotelCharges);}

    }

    private static void altaFunctie(HotelCharges hotelCharges) {
//        hotelCharges.computeTotal();
        System.out.println("FEE: " + hotelCharges.getTotalFee() + "\n");
    }
}

class HotelCharges {
    private final List<HotelDayCharge> days = new ArrayList<>();
    private double totalFee;

    private double computeTotal() {
        final double BREAKFAST_FEE = 10;
        final double PARKING_HOUR_RATE = 2;
        double totalFee = 0;
        for (HotelDayCharge day : days) {
            totalFee += day.getDayRate();
            if (day.isBreakfast()) {
                totalFee += BREAKFAST_FEE;
            }
            totalFee += day.getParkingHours() * PARKING_HOUR_RATE;
        }
        return totalFee;
    }

    // creepy
//    public void forEach(Consumer<HotelDayCharge> action) {
//        days.forEach(action);
//    }
//    public List<HotelDayCharge> getDays() {
//        return new ArrayList<>(days); // gc unhappy
//    }
    public List<HotelDayCharge> getDays() {
        return Collections.unmodifiableList(days); // cea mai comuna
    }
//    public Iterable<HotelDayCharge> getDays() {
//        return days;
//    }
//    public Collection<? extends HotelDayCharge> getDays() {
//        return days;
//    }
//    public Iterable<HotelDayCharge> getDays() {
//        return days;
//    }
    public void addDay(HotelDayCharge dayCharge) {
        days.add(dayCharge);
        totalFee = computeTotal();
    }

    public double getTotalFee() {
        return totalFee;
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