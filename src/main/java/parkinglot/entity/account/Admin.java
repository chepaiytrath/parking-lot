package parkinglot.entity.account;

import parkinglot.entity.parking.ParkingFloor;
import parkinglot.entity.parking.ParkingLot;
import parkinglot.entity.parking.ParkingSpot;
import parkinglot.entity.parking.panel.EntrancePanel;
import parkinglot.entity.parking.panel.ExitPanel;
import parkinglot.exception.ParkingFloorAlreadyCreatedException;
import parkinglot.exception.ParkingFloorNotFoundException;
import parkinglot.exception.ParkingLotNotFoundException;
import parkinglot.exception.ParkingSpotAlreadyCreatedException;
import parkinglot.repository.ParkingLotRepository;

public class Admin extends Account {
    ParkingLotRepository repository = new ParkingLotRepository();

    public ParkingLot createParkingLot() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        repository.addParkingLot(parkingLot);
        return parkingLot;
    }

    public void addParkingFloor(String parkingLotId, ParkingFloor floor) throws ParkingFloorAlreadyCreatedException, ParkingLotNotFoundException {
        repository.addParkingFloor(ParkingLot.getInstance().getParkingLotId(), floor);
    }

    public void addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) throws ParkingFloorNotFoundException, ParkingSpotAlreadyCreatedException, ParkingLotNotFoundException {
        repository.addParkingSpot(parkingLotId, parkingFloorId, parkingSpot);
    }

    public void addEntrancePanel(String parkingLotId, EntrancePanel entrancePanel) throws ParkingLotNotFoundException {
        repository.addEntrancePanel(parkingLotId, entrancePanel);
    }

    public void addExitPanel(String parkingLotId, ExitPanel exitPanel) throws ParkingLotNotFoundException {
        repository.addExitPanel(parkingLotId, exitPanel);
    }
}