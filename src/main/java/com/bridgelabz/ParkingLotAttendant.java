package com.bridgelabz;

import java.util.HashMap;

public class ParkingLotAttendant {
    ParkingOwner parkingOwner = new ParkingOwner();
    public Integer PARKING_SLOT_SIZE = 5;

//    private Object vehicle;
    private boolean isParkingFull;
    public IParkingLotInFormation parkingLotInformationSubscriber;

    public ParkingLotAttendant() {
        Driver driver;
        parkingLotInformationSubscriber = new ParkingLotInformationSubscriber();
    }

    public Integer getParkingSlot() {
        return this.PARKING_SLOT_SIZE;
    }

    //METHOD TO PARK THE VEHICLE
    public boolean isPark(Integer parkingSlotNumber, Object vehicleToPark) throws ParkingLotException {
        if (parkingOwner.parkingMap.isEmpty()) {
            parkingOwner.parkingMap.put(parkingSlotNumber, vehicleToPark);
            return true;
        } else if (!parkingOwner.parkingMap.isEmpty() && (!parkingOwner.parkingMap.containsKey(parkingSlotNumber)) && (parkingOwner.parkingMap.size() < getParkingSlot())) {
            parkingOwner.parkingMap.put(parkingSlotNumber, vehicleToPark);
            return true;
        } else isParkingFull = true;
        parkingLotInformationSubscriber.notifyParkingStatus(true);
        return true;
    }

    //METHOD TO UNPARK THE GIVEN VEHICLE
    public boolean unParkTheVehicle(Integer key) throws ParkingLotException {
        if (parkingOwner.parkingMap.containsKey(key)) {
            parkingOwner.parkingMap.remove(key);
            parkingLotInformationSubscriber.notifyParkingStatus(false);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_PARK_HERE, "Vehicle Is Not Parked Here");
    }
}
