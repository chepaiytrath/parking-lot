package parkinglot.entity.vehicle;

import parkinglot.enums.VehicleType;

public class CarVehicle extends Vehicle {
    public CarVehicle(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}
