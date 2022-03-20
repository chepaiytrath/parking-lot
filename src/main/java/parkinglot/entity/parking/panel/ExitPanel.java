package parkinglot.entity.parking.panel;

import lombok.Getter;
import parkinglot.entity.parking.ParkingLot;
import parkinglot.entity.parking.ParkingSpot;
import parkinglot.entity.payment.HourlyCost;
import parkinglot.entity.payment.ParkingTicket;
import parkinglot.enums.ParkingSpotType;
import parkinglot.repository.ParkingLotRepository;
import parkinglot.service.ParkingService;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class ExitPanel {
    private ParkingService parkingService = new ParkingService();

    private String exitPanelId;

    public ExitPanel(String exitPanelId){
        this.exitPanelId = exitPanelId;
    }

    public ParkingTicket scanTicketAndVacateSpot(String parkingLotId, ParkingTicket ticket){
        String allocatedParkingSpotId = ticket.getAllocatedSpotId();
        ParkingLot parkingLot = ParkingLotRepository.getParkingLot(parkingLotId);

        ParkingSpot parkingSpot = parkingService.vacateParkingSpot(parkingLot, allocatedParkingSpotId);

        double cost = calculateCost(ticket, parkingSpot.getParkingSpotType());
        ticket.setCharges(cost);
        return ticket;
    }

    private double calculateCost(ParkingTicket ticket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(ticket.getIssuedAt(), LocalDateTime.now());

        long hours = duration.toHours();
        if(hours == 0){
            hours = 1;
        }
        double amount = hours * new HourlyCost().getCost(parkingSpotType);
        return amount;
    }
}