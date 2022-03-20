package parkinglot.entity.parking.spot;

import parkinglot.entity.parking.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class HandicappedParkingSpot extends ParkingSpot {
    public HandicappedParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.HANDICAPPED);
    }
}
