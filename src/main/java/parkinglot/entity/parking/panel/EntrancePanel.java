package parkinglot.entity.parking.panel;

import lombok.Getter;
import parkinglot.entity.parking.ParkingLot;
import parkinglot.entity.parking.ParkingSpot;
import parkinglot.entity.payment.ParkingTicket;
import parkinglot.entity.vehicle.Vehicle;
import parkinglot.enums.ParkingTicketStatus;
import parkinglot.exception.ParkingLotFullException;
import parkinglot.repository.ParkingLotRepository;
import parkinglot.service.ParkingService;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class EntrancePanel {
    private ParkingService parkingService = new ParkingService();

    private String entrancePanelId;

    public EntrancePanel(String entrancePanelId) {
        this.entrancePanelId = entrancePanelId;
    }

    public ParkingTicket getParkingTicket(String parkingLotId, Vehicle vehicle) throws ParkingLotFullException {
        //Can park the vehicle type? : available spot > 0
        ParkingLot parkingLot = ParkingLotRepository.getParkingLot(parkingLotId);

        if (!parkingService.canPark(parkingLot, vehicle)) {
            throw new ParkingLotFullException();
        }

        //Get spot to add into the parking tkt
        ParkingSpot parkingSpot = parkingService.getSpot(parkingLot, vehicle.getVehicleType());

        parkingSpot.assignVehicleToSpot(vehicle); //TODO Check if this is OK to do

        //Build ticket and give to vehicle
        ParkingTicket tkt = buildParkingTicket(vehicle.getLicenseNumber(), parkingSpot.getParkingSpotId());
        return tkt;
    }

    private ParkingTicket buildParkingTicket(String vehicleLicenseNumber, String parkingSpotId) {
        ParkingTicket tkt = ParkingTicket.builder()
                .allocatedSpotId(parkingSpotId)
                .issuedAt(LocalDateTime.now())
                .licenseNumber(vehicleLicenseNumber)
                .status(ParkingTicketStatus.ACTIVE)
                .ticketNumber(UUID.randomUUID().toString())
                .build();

        return tkt;
    }
}