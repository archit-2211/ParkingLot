package pricing.Observer;



import models.Ticket;

import java.time.DayOfWeek;

public class WeekendSurchargeObserver implements FareObserver {

    private static final double SURCHARGE_PERCENT = 0.20; // 20%

    @Override
    public double applySurcharge(Ticket ticket, double currentFare) {
        DayOfWeek day = ticket.getEntryTime().getDayOfWeek();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return currentFare * SURCHARGE_PERCENT;
        }
        return 0;
    }
}
