package models;



import java.time.LocalDateTime;

public class EntryGate extends Gate {

    public EntryGate(String gateId) {
        super(gateId);
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot spot) {
        return new Ticket(vehicle, spot, LocalDateTime.now());
    }
}
