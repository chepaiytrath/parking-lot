package parkinglot.service;

import parkinglot.entity.parking.ParkingLot;
import parkinglot.entity.parking.ParkingSpot;
import parkinglot.entity.vehicle.Vehicle;
import parkinglot.enums.VehicleType;
import parkinglot.exception.ParkingLotFullException;

public class ParkingService {


    public boolean canPark(ParkingLot parkingLot, Vehicle vehicle) {
        return parkingLot.canPark(vehicle);
    }

    public ParkingSpot getSpot(ParkingLot parkingLot, VehicleType vehicleType) throws ParkingLotFullException {
        return parkingLot.getParkingSpot(vehicleType);
    }

    public ParkingSpot vacateParkingSpot(ParkingLot parkingLot, String allocatedParkingSpotId) {
        return parkingLot.vacateParkingSpot(allocatedParkingSpotId);
    }
}
