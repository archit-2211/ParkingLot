package pricing.Observer;



import models.Ticket;

import java.time.LocalTime;

public class PeakHourSurchargeObserver implements FareObserver {

    @Override
    public double applySurcharge(Ticket ticket, double currentFare) {
        LocalTime entryTime = ticket.getEntryTime().toLocalTime();

        boolean isPeakHour =
                (entryTime.isAfter(LocalTime.of(18, 0)) &&
                 entryTime.isBefore(LocalTime.of(22, 0)));

        return isPeakHour ? 30.0 : 0.0;
    }
}
