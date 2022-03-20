package parkinglot.entity.parking.spot;

import parkinglot.entity.parking.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class LargeParkingSpot extends ParkingSpot {
    public LargeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.LARGE);
    }
}
