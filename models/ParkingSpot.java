package models;



import enums.ParkingStatus; 
import enums.VehicleType;

public class ParkingSpot {

    private final String spotId;
    private final VehicleType supportedType;
    private ParkingStatus status;

    public ParkingSpot(String spotId, VehicleType supportedType) {
        this.spotId = spotId;
        this.supportedType = supportedType;
        this.status = ParkingStatus.AVAILABLE;
    }

    public boolean canPark(Vehicle vehicle) {
        return status == ParkingStatus.AVAILABLE &&
               supportedType == vehicle.getType();
    }

    public void occupy() {
        status = ParkingStatus.OCCUPIED;
    }

    public void free() {
        status = ParkingStatus.AVAILABLE;
    }

    public String getSpotId() {
        return spotId;
    }
}
