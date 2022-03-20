package parkinglot.entity.parking.spot;

import parkinglot.entity.parking.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class MotorbikeParkingSpot extends ParkingSpot {
    public MotorbikeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.MOTORBIKE);
    }
}
