package parkinglot.entity.vehicle;

import parkinglot.enums.VehicleType;

public class VanVehicle extends Vehicle {
    public VanVehicle(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }
}
