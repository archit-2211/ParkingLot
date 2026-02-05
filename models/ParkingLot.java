package models;



import java.util.List;

public class ParkingLot {

    private final String name;
    private final List<ParkingFloor> floors;

    public ParkingLot(String name, List<ParkingFloor> floors) {
        this.name = name;
        this.floors = floors;
    }

    public ParkingSpot assignSpot(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle);
            if (spot != null) {
                spot.occupy();
                return spot;
            }
        }
        return null;
    }
}
