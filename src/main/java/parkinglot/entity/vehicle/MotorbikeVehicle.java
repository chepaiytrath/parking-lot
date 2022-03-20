package parkinglot.entity.vehicle;

import parkinglot.enums.VehicleType;

public class MotorbikeVehicle extends Vehicle {
    public MotorbikeVehicle(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORBIKE);
    }
}
