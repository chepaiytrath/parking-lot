package parkinglot.entity.vehicle;

import parkinglot.enums.VehicleType;

public class TruckVehicle extends Vehicle {
    public TruckVehicle(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }
}
