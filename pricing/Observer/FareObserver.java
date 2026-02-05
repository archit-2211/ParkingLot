package pricing.Observer;



import models.Ticket;

public interface FareObserver {
    double applySurcharge(Ticket ticket, double currentFare);
}

