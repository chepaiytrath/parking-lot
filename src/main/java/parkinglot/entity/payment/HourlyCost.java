package parkinglot.entity.payment;

import parkinglot.enums.ParkingSpotType;

import java.util.HashMap;
import java.util.Map;

public class HourlyCost {
    private Map<ParkingSpotType, Double> hourlyCharges = new HashMap<>();

    public HourlyCost(){
        hourlyCharges.put(ParkingSpotType.CAR, 20.0);
        hourlyCharges.put(ParkingSpotType.LARGE, 30.0);
        hourlyCharges.put(ParkingSpotType.ELECTRIC, 25.0);
        hourlyCharges.put(ParkingSpotType.MOTORBIKE, 10.0);
        hourlyCharges.put(ParkingSpotType.HANDICAPPED, 25.0);
    }

    public Double getCost(ParkingSpotType parkingSpotType){
        return hourlyCharges.get(parkingSpotType);
    }
}