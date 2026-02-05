package models;



import java.util.List;

public class ParkingFloor {

    private final String floorId;
    private final List<ParkingSpot> spots;

    public ParkingFloor(String floorId, List<ParkingSpot> spots) {
        this.floorId = floorId;
        this.spots = spots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canPark(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public String getFloorId() {
        return floorId;
    }
}
