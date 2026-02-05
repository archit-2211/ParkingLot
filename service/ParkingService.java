package service;

import models.EntryGate;
import models.ExitGate;
import models.ParkingLot;
import models.ParkingSpot;
import models.PaymentSlip;
import models.Ticket;
import models.Vehicle;

public class ParkingService {

    private final ParkingLot parkingLot;
    private final EntryGate entryGate;
    private final ExitGate exitGate;

    public ParkingService(
            ParkingLot parkingLot,
            EntryGate entryGate,
            ExitGate exitGate
    ) {
        this.parkingLot = parkingLot;
        this.entryGate = entryGate;
        this.exitGate = exitGate;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkingLot.assignSpot(vehicle);
        if (spot == null) {
            throw new RuntimeException("No parking spot available");
        }
        return entryGate.generateTicket(vehicle, spot);
    }

    public PaymentSlip exitVehicle(Ticket ticket) {
        return exitGate.processExit(ticket);
    }
}
