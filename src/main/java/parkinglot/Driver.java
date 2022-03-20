package parkinglot;


import parkinglot.entity.account.Account;
import parkinglot.entity.account.Address;
import parkinglot.entity.account.Admin;
import parkinglot.entity.parking.ParkingFloor;
import parkinglot.entity.parking.ParkingLot;
import parkinglot.entity.parking.ParkingSpot;
import parkinglot.entity.parking.panel.EntrancePanel;
import parkinglot.entity.parking.panel.ExitPanel;
import parkinglot.entity.parking.spot.CarParkingSpot;
import parkinglot.entity.parking.spot.MotorbikeParkingSpot;
import parkinglot.entity.payment.ParkingTicket;
import parkinglot.entity.payment.Payment;
import parkinglot.entity.vehicle.CarVehicle;
import parkinglot.entity.vehicle.MotorbikeVehicle;
import parkinglot.entity.vehicle.VanVehicle;
import parkinglot.entity.vehicle.Vehicle;
import parkinglot.enums.ParkingSpotType;
import parkinglot.enums.VehicleType;
import parkinglot.exception.*;

import java.util.UUID;

public class Driver {
    public static void main(String[] args) throws ParkingFloorAlreadyCreatedException, ParkingLotNotFoundException, ParkingFloorNotFoundException, ParkingSpotAlreadyCreatedException, ParkingLotFullException {
        //Admin tests
        Account adminAccount = new Admin();

        ParkingLot parkingLot = ((Admin) adminAccount).createParkingLot();
        String parkingLotId = parkingLot.getParkingLotId();

        Address address = new Address();
        address.setAddressLine1("Ram parking Complex");
        address.setStreet("BG Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560075");

        parkingLot.setAddress(address);

        //Admin Case 1 - should be able to add parking floor case
        ((Admin) adminAccount).addParkingFloor(parkingLotId, new ParkingFloor("1"));
        //Admin Case 2 - should be able to add parking floor case
        ((Admin) adminAccount).addParkingFloor(parkingLotId, new ParkingFloor("2"));

        //Admin Case 3 - should be able to add entrance panel
        EntrancePanel entrancePanel = new EntrancePanel("1");
        ((Admin) adminAccount).addEntrancePanel(parkingLotId, entrancePanel);

        //Admin Case 4 - should be able to add exit panel
        ExitPanel exitPanel = new ExitPanel("1");
        ((Admin) adminAccount).addExitPanel(parkingLotId, exitPanel);

        String floorId = parkingLot.getFloors().get(0).getParkingFloorId();

        ///Admin case 5 - should be able to add car parking spot
        ParkingSpot carSpot1 = new CarParkingSpot("c1");
        ((Admin) adminAccount).addParkingSpot(parkingLotId, floorId, carSpot1);
        ///Admin case 6 - should be able to add bike parking spot
        ParkingSpot bikeSport = new MotorbikeParkingSpot("b1");
        ((Admin) adminAccount).addParkingSpot(parkingLotId, floorId, bikeSport);
        ///Admin case 7 - should be able to add car parking spot
        ParkingSpot carSpot2 = new CarParkingSpot("c2");
        ((Admin) adminAccount).addParkingSpot(parkingLotId, floorId, carSpot2);

        // Test case 1 - check for availability of parking lot - TRUE
        System.out.println(parkingLot.canPark(VehicleType.CAR));

        // Test case 2 - check for availability of parking lot - FALSE
        System.out.println(parkingLot.canPark(VehicleType.ELECTRIC_BIKE));

        // Test case 3 - check for availability of parking lot - FALSE
        System.out.println(parkingLot.canPark(VehicleType.ELECTRIC));

        // TEST case 4 - Check if full
        System.out.println(parkingLot.isLotFull());

        // Test case 5 - get parking spot
        Vehicle vehicle = new CarVehicle("KA05MR2311");
        ParkingSpot availableSpot = parkingLot.getParkingSpot(vehicle.getVehicleType());
        System.out.println(availableSpot.getParkingSpotType());
        System.out.println(availableSpot.getParkingSpotId());

        // Test case 6 - should not be able to get spot
        Vehicle van = new VanVehicle("1234567890");
        ParkingSpot vanSpot = parkingLot.getParkingSpot(van.getVehicleType());
        System.out.println(null == vanSpot);

        //Test case 7 - Entrance Panel - 1
        System.out.println(parkingLot.getEntrances().size());

        // Test case - 8 - Should be able to get parking ticket
        ParkingTicket parkingTicket = entrancePanel.getParkingTicket(parkingLotId, vehicle);
        System.out.println(parkingTicket.getAllocatedSpotId());

        ((Admin) adminAccount).addParkingSpot(parkingLotId, floorId, carSpot1);
        // Test case - 9 - Should be able to get parking ticket
        Vehicle car = new CarVehicle("KA02MR6355");
        ParkingTicket parkingTicket1 = entrancePanel.getParkingTicket(parkingLotId, car);

        // Test case 10 - Should not be able to get ticket
        ParkingTicket tkt = entrancePanel.getParkingTicket(parkingLotId, new CarVehicle("986776580"));
        System.out.println(null == tkt);

        // Test case 11 - Should be able to get ticket
        ParkingTicket mtrTkt = entrancePanel.getParkingTicket(parkingLotId, new MotorbikeVehicle("98745413"));
        System.out.println(mtrTkt.getAllocatedSpotId());

        //Test case 12 - vacate parking spot
        mtrTkt = exitPanel.scanTicketAndVacateSpot(parkingLotId, mtrTkt);
        System.out.println(mtrTkt.getCharges());
        System.out.println(mtrTkt.getCharges() > 0);

        // Test case 13 - park on vacated spot
        ParkingTicket mtrTkt1 = entrancePanel.getParkingTicket(parkingLotId, new MotorbikeVehicle("897875463"));
        System.out.println(mtrTkt.getAllocatedSpotId());

        // Test case 14 - park when spot is not available
        ParkingTicket unavailablemTkt =
                entrancePanel.getParkingTicket(parkingLotId, new MotorbikeVehicle("456789123"));
        System.out.println(null == unavailablemTkt);

        // Test cast 15 - vacate car
        parkingTicket = exitPanel.scanTicketAndVacateSpot(parkingLotId, parkingTicket);
        System.out.println(parkingTicket.getCharges());
        System.out.println(parkingTicket.getCharges() > 0);

        //Test case 16 - Now should be able to park car
        System.out.println(parkingLot.canPark(VehicleType.CAR));

        //Test case 17 - Should be able to vacate parked vehicle
        parkingTicket1 = exitPanel.scanTicketAndVacateSpot(parkingLotId, parkingTicket1);
        System.out.println(parkingTicket1.getCharges());
        System.out.println(parkingTicket1.getCharges() > 0);

        //Test case 18 - check for slots count
        System.out.println(parkingLot.getFloors()
                .get(0).getAvailableSpotsByType().get(ParkingSpotType.CAR).size());

        //Test case 19 - Payment
        Payment payment = new Payment(UUID.randomUUID().toString(),
                parkingTicket1.getTicketNumber(), parkingTicket1.getCharges());
        payment.makePayment();
        System.out.println(payment.getPaymentStatus());

        //Test case 20 - vacate motorbike spot
        mtrTkt = exitPanel.scanTicketAndVacateSpot(parkingLotId, mtrTkt);
        System.out.println(parkingLot.getFloors()
                .get(0).getAvailableSpotsByType().get(ParkingSpotType.MOTORBIKE).size());
        System.out.println(mtrTkt.getCharges());
    }
}