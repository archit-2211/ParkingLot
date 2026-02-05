package pricing;


import models.Ticket;

public interface FareCalculationStrategy {
    double calculateFare(Ticket ticket);
}
