package parkinglot.entity.parking.spot;

import parkinglot.entity.parking.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class CarParkingSpot extends ParkingSpot {
    public CarParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.CAR);
    }
}