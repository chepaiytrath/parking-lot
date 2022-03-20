package parkinglot.entity.parking;

import lombok.Getter;
import lombok.Setter;
import parkinglot.entity.account.Address;
import parkinglot.entity.parking.panel.EntrancePanel;
import parkinglot.entity.parking.panel.ExitPanel;
import parkinglot.entity.vehicle.Vehicle;
import parkinglot.enums.VehicleType;
import parkinglot.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;

@Getter
public class ParkingLot {
    String parkingLotId;
    @Setter
    Address address;

    private List<ParkingFloor> floors;
    private List<EntrancePanel> entrances;
    private List<ExitPanel> exits;

    private ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString();
        this.floors = new ArrayList<>();
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    private static ParkingLot INSTANCE;

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            synchronized (ParkingLot.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ParkingLot();
                }
            }
        }
        return INSTANCE;
    }

    public boolean canPark(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            if (floor.canPark(vehicle.getVehicleType())) {
                return true;
            }
        }
        return false;
    }

    public boolean canPark(VehicleType vehicleType) {
        for (ParkingFloor floor : floors) {
            if (floor.canPark(vehicleType)) {
                return true;
            }
        }
        return false;
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType) throws ParkingLotFullException {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.getParkingSpot(vehicleType);
            if(spot != null){
                return spot;
            }
        }
        return null;
    }

    public ParkingSpot vacateParkingSpot(String allocatedParkingSpotId) {
        ParkingSpot parkingSpot = null;
        for (ParkingFloor floor : floors) {
            parkingSpot = floor.vacateParkingSpot(allocatedParkingSpotId);
            if(parkingSpot != null){
                break;
            }
        }
        return parkingSpot;
    }

    public boolean isLotFull(){
        BitSet bitSet = new BitSet();
        int floorIndex = 0;
        for(ParkingFloor floor : floors){
            bitSet.set(floorIndex, floor.isFloorFull());
            floorIndex++;
        }
        return bitSet.cardinality() == floors.size();
    }
}