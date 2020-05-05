package com.bridgelabz;

public class ParkingLotInformationSubscriber implements IParkingLotInFormation{
    //FIELDS
    private ParkingOwner parkingLotOwner;
    private AirportSecurity airportSecurity;

    //CONSTRUCTOR
    public ParkingLotInformationSubscriber() {
        airportSecurity = new AirportSecurity();
        parkingLotOwner = new ParkingOwner();
    }

    @Override
    public void notifyParkingStatus(boolean status) throws ParkingLotException {
        airportSecurity.updateParkingLotStatus(status);
        parkingLotOwner.updateParkingLotStatus(status);
    }
}
