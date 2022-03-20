package parkinglot.repository;

import parkinglot.entity.parking.ParkingFloor;
import parkinglot.entity.parking.ParkingLot;
import parkinglot.entity.parking.ParkingSpot;
import parkinglot.entity.parking.panel.EntrancePanel;
import parkinglot.entity.parking.panel.ExitPanel;
import parkinglot.enums.ParkingSpotType;
import parkinglot.exception.ParkingFloorNotFoundException;
import parkinglot.exception.ParkingLotNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLotRepository {
    public static Map<String, ParkingLot> parkingLotMap = new HashMap<>();
    //public static List<ParkingLot> parkingLots = new ArrayList();     //TODO : Understand usage

    public static ParkingLot addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.putIfAbsent(parkingLot.getParkingLotId(), parkingLot);
        //parkingLots.add(parkingLot);
        return parkingLot;
    }

    public static ParkingLot getParkingLot(String parkingLotId) {
        return parkingLotMap.get(parkingLotId);
    }

    public static ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor) throws ParkingLotNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<ParkingFloor> floorDb = lot.getFloors().stream()
                .filter(f -> f.getParkingFloorId().equalsIgnoreCase(parkingFloor.getParkingFloorId()))
                .findFirst();
        if (floorDb.isPresent()) {
            return floorDb.get();
        }
        lot.getFloors().add(parkingFloor);
        return parkingFloor;
    }

    public static Optional<ParkingFloor> getParkingFloor(String parkingLotId, String parkingFloorId) throws ParkingLotNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<ParkingFloor> floor = lot.getFloors().stream()
                .filter(f -> f.getParkingFloorId().equalsIgnoreCase(parkingFloorId))
                .findFirst();
        return floor;
    }


    public static ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) throws ParkingLotNotFoundException, ParkingFloorNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }

        Optional<ParkingFloor> floor = lot.getFloors().stream()
                .filter(f -> f.getParkingFloorId().equalsIgnoreCase(parkingFloorId))
                .findFirst();
        if (!floor.isPresent()) {
            throw new ParkingFloorNotFoundException();
        }

        Optional<ParkingSpot> spotDb = floor.get().getAvailableSpotsByType().get(parkingSpot.getParkingSpotType())
                .stream()
                .filter(s -> s.getParkingSpotId().equals(parkingSpot.getParkingSpotId()))
                .findFirst();

        if (spotDb.isPresent()) {
            return spotDb.get();
        }

        floor.get().getAvailableSpotsByType().get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        return parkingSpot;
    }

    public static Optional<ParkingSpot> getAvailableParkingSpot(String parkingLotId,
                                                         String parkingFloorId,
                                                         ParkingSpotType parkingSpotType,
                                                         String parkingSpotId) throws ParkingLotNotFoundException, ParkingFloorNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<ParkingFloor> floor = lot.getFloors().stream()
                .filter(f -> f.getParkingFloorId().equalsIgnoreCase(parkingFloorId))
                .findFirst();
        if (!floor.isPresent()) {
            throw new ParkingFloorNotFoundException();
        }

        Optional<ParkingSpot> usedSpot = floor.get().getAvailableSpotsByType().get(parkingSpotType)
                .stream()
                .filter(s -> s.getParkingSpotId().equalsIgnoreCase(parkingSpotId))
                .findFirst();

        return usedSpot;
    }

    public static Optional<ParkingSpot> getUsedParkingSpot(String parkingLotId, String parkingFloorId, String parkingSpotId) throws ParkingLotNotFoundException, ParkingFloorNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<ParkingFloor> floor = lot.getFloors().stream()
                .filter(f -> f.getParkingFloorId().equalsIgnoreCase(parkingFloorId))
                .findFirst();
        if (!floor.isPresent()) {
            throw new ParkingFloorNotFoundException();
        }

        Optional<ParkingSpot> usedSpot = floor.get().getUsedSpots().values()
                .stream()
                .filter(s -> s.getParkingSpotId().equalsIgnoreCase(parkingSpotId))
                .findFirst();

        return usedSpot;
    }

    public static EntrancePanel addEntrancePanel(String parkingLotId, EntrancePanel entrancePanel) throws ParkingLotNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<EntrancePanel> entrancePanelDb = lot.getEntrances().stream()
                .filter(e -> e.getEntrancePanelId().equals(entrancePanel.getEntrancePanelId()))
                .findFirst();

        if (entrancePanelDb.isPresent()) {
            return entrancePanelDb.get();
        }
        lot.getEntrances().add(entrancePanel);
        return entrancePanel;
    }


    public static Optional<EntrancePanel> getEntrancePanel(String parkingLotId, String entrancePanelId) throws ParkingLotNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<EntrancePanel> entrancePanel = lot.getEntrances()
                .stream()
                .filter(e -> e.getEntrancePanelId().equals(entrancePanelId))
                .findFirst();
        return entrancePanel;
    }

    public static ExitPanel addExitPanel(String parkingLotId, ExitPanel exitPanel) throws ParkingLotNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<ExitPanel> exitPanelDb = lot.getExits().stream()
                .filter(e -> e.getExitPanelId().equals(exitPanel.getExitPanelId()))
                .findFirst();

        if (exitPanelDb.isPresent()) {
            return exitPanelDb.get();
        }
        lot.getExits().add(exitPanel);
        return exitPanel;
    }


    public static Optional<ExitPanel> getExitPanel(String parkingLotId, String exitPanelId) throws ParkingLotNotFoundException {
        ParkingLot lot = parkingLotMap.get(parkingLotId);
        if (lot == null) {
            throw new ParkingLotNotFoundException();
        }
        Optional<ExitPanel> entrancePanel = lot.getExits()
                .stream()
                .filter(e -> e.getExitPanelId().equals(exitPanelId))
                .findFirst();
        return entrancePanel;
    }
}
