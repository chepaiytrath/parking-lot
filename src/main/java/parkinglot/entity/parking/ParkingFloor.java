package parkinglot.entity.parking;

import lombok.Getter;
import parkinglot.enums.ParkingSpotType;
import parkinglot.enums.VehicleType;
import parkinglot.exception.ParkingLotFullException;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

import static parkinglot.enums.ParkingSpotType.*;

@Getter
public class ParkingFloor {
    private String parkingFloorId;

    private Map<ParkingSpotType, Deque<ParkingSpot>> availableSpotsByType;
    private Map<String, ParkingSpot> usedSpots;     //key = ParkingSpot.id

    public ParkingFloor(String floorId) {
        this.parkingFloorId = floorId;
        this.availableSpotsByType = new HashMap<>();
        this.usedSpots = new HashMap<>();

        this.availableSpotsByType.put(HANDICAPPED, new ConcurrentLinkedDeque<>());
        this.availableSpotsByType.put(MOTORBIKE, new ConcurrentLinkedDeque<>());
        this.availableSpotsByType.put(CAR, new ConcurrentLinkedDeque<>());
        this.availableSpotsByType.put(LARGE, new ConcurrentLinkedDeque<>());
        this.availableSpotsByType.put(ELECTRIC, new ConcurrentLinkedDeque<>());
        this.availableSpotsByType.put(ELECTRIC_BIKE, new ConcurrentLinkedDeque<>());
    }

    public static ParkingSpotType mapParkingSpotTypeToVehicleType(VehicleType vehicleType) {
        switch (vehicleType) {
            case MOTORBIKE: {
                return MOTORBIKE;
            }
            case CAR: {
                return CAR;
            }
            case ELECTRIC: {
                return ELECTRIC;
            }
            case ELECTRIC_BIKE: {
                return ELECTRIC_BIKE;
            }
            default: {
                //VAN, TRUCK, AND OTHERS
                return LARGE;
            }
        }
    }

    public boolean canPark(VehicleType vehicleType) {
        return canPark(mapParkingSpotTypeToVehicleType(vehicleType));
    }

    public boolean canPark(ParkingSpotType parkingSpotType) {
        return availableSpotsByType.get(parkingSpotType).size() > 0;
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType) throws ParkingLotFullException {
        return getParkingSpot(mapParkingSpotTypeToVehicleType(vehicleType));
    }

    private synchronized ParkingSpot getParkingSpot(ParkingSpotType parkingSpotType) throws ParkingLotFullException {
        if (!canPark(parkingSpotType)) {
            throw new ParkingLotFullException();
        }
        ParkingSpot parkingSpot = availableSpotsByType.get(parkingSpotType).poll();
        usedSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
        return parkingSpot;
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotId) {
        ParkingSpot parkingSpot = usedSpots.remove(parkingSpotId);

        if (parkingSpot != null) {
            parkingSpot.freeSpot();
            availableSpotsByType.get(parkingSpot.getParkingSpotType()).addFirst(parkingSpot);
        }
        return parkingSpot;
    }

    public boolean isFloorFull() {
        int parkingSpotTypeIndex = 0;
        BitSet bitSet = new BitSet();
        for (Map.Entry<ParkingSpotType, Deque<ParkingSpot>> entry : availableSpotsByType.entrySet()) {
            bitSet.set(parkingSpotTypeIndex++, entry.getValue().size() == 0);
        }
        return bitSet.cardinality() == availableSpotsByType.entrySet().size();
    }
}