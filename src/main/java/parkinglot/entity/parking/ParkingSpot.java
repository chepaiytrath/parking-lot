package parkinglot.entity.parking;

import lombok.Getter;
import parkinglot.entity.vehicle.Vehicle;
import parkinglot.enums.ParkingSpotType;

@Getter
public abstract class ParkingSpot {

    private String parkingSpotId;
    private boolean isFree;
    private String assignedVehicleId;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
    }

    public void assignVehicleToSpot(Vehicle vehicle) {
        this.assignedVehicleId = vehicle.getLicenseNumber();
        this.isFree = false;
    }

    public void freeSpot() {
        this.isFree = true;
        this.assignedVehicleId = null;
    }
}
