package parkinglot.entity.vehicle;

import parkinglot.enums.VehicleType;

public class ElectricCarVehicle extends Vehicle {
    public ElectricCarVehicle(String licenseNumber) {
        super(licenseNumber, VehicleType.ELECTRIC);
    }
}
