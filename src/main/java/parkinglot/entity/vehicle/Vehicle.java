package parkinglot.entity.vehicle;

import lombok.Getter;
import parkinglot.entity.payment.ParkingTicket;
import parkinglot.enums.VehicleType;

@Getter
public abstract class Vehicle {
    private String licenseNumber;
    private VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }
}
