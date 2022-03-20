package parkinglot.entity.vehicle;

import parkinglot.enums.VehicleType;

public class ElectricMotorbikeVehicle extends Vehicle {
    public ElectricMotorbikeVehicle(String licenseNumber) {
        super(licenseNumber, VehicleType.ELECTRIC_BIKE);
    }
}
