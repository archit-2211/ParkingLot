package pricing.Observer;


import models.Ticket;

public class FestivalSurchargeObserver implements FareObserver {

    private final boolean isFestivalDay;

    public FestivalSurchargeObserver(boolean isFestivalDay) {
        this.isFestivalDay = isFestivalDay;
    }

    @Override
    public double applySurcharge(Ticket ticket, double currentFare) {
        return isFestivalDay ? 50.0 : 0.0;
    }
}
