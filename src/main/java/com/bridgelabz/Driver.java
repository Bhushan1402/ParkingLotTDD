package com.bridgelabz;

public class Driver {
    public IParkingLotInFormation parkingLotInformationSubscriber;
    public ParkingOwner parkingOwner = new ParkingOwner();
    public Driver driver;
    Object vehicle = new Object();

    public Driver() {
        parkingLotInformationSubscriber = new ParkingLotInformationSubscriber();
        ParkingOwner parkingOwner = new ParkingOwner();
    }

    //METHOD TO UNPARK THE VEHICLE
    public boolean unParkTheVehicle(Object vehicle) throws ParkingLotException {
        parkingOwner.parkingMap.containsKey(vehicle);
        if (parkingOwner.parkingMap.isEmpty()) {
            parkingOwner.parkingMap.remove(vehicle);
            parkingLotInformationSubscriber.notifyParkingStatus(false);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_PARK_HERE, "Vehicle Is Not Parked Here");
    }
}
