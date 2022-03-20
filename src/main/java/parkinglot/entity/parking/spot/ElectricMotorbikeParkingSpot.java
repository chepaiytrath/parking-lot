package parkinglot.entity.parking.spot;

import parkinglot.entity.parking.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class ElectricMotorbikeParkingSpot extends ParkingSpot {
    public ElectricMotorbikeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.ELECTRIC_BIKE);
    }
}
