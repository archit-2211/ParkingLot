package pricing;

import java.util.ArrayList;
import java.util.List;

import models.Ticket;

import pricing.Observer.*;

public class FareCalculator implements FareSubject {

    private final FareCalculationStrategy strategy;
    private final List<FareObserver> observers = new ArrayList<>();

    public FareCalculator(FareCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void registerObserver(FareObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(FareObserver observer) {
        observers.remove(observer);
    }

    public double calculateFare(Ticket ticket) {
        double baseFare = strategy.calculateFare(ticket);
        System.out.println("Base Fare : " +  baseFare) ;
        double finalFare = baseFare;
        for (FareObserver observer : observers) {
            finalFare += observer.applySurcharge(ticket, finalFare);
        }

        return finalFare;
    }
}
