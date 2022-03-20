package parkinglot.entity.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import parkinglot.enums.ParkingTicketStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ParkingTicket {
    LocalDateTime issuedAt;
    String licenseNumber;
    String ticketNumber;
    ParkingTicketStatus status;
    String allocatedSpotId;
    double charges;
}