package pricing ; 

import models.Ticket;

import java.time.Duration;

public class HourlyFareStrategy implements FareCalculationStrategy {

    private final double hourlyRate;

    public HourlyFareStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateFare(Ticket ticket) {
        long hours = Duration.between(
                ticket.getEntryTime(),
                ticket.getExitTime()
        ).toHours() + 1;

        return hours * hourlyRate;
    }
}
