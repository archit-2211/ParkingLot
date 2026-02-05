package models ; 



import pricing.FareCalculator;

import java.time.LocalDateTime;

public class ExitGate extends Gate {

    private final FareCalculator fareCalculator;

    public ExitGate(String gateId, FareCalculator fareCalculator) {
        super(gateId);
        this.fareCalculator = fareCalculator;
    }

    public PaymentSlip processExit(Ticket ticket) {
        ticket.setExitTime(LocalDateTime.now());
        double amount = fareCalculator.calculateFare(ticket);
        ticket.getSpot().free();
        return new PaymentSlip(ticket, amount);
    }
}
