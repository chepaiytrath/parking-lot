package parkinglot.entity.parking.spot;

import parkinglot.entity.parking.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class ElectricCarParkingSpot extends ParkingSpot {
    public ElectricCarParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.ELECTRIC);
    }
}
