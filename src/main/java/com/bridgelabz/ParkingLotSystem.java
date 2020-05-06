package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLotSystem {

    List<Object> vehicle = new ArrayList<Object>();
    int parkingLotCapacity = 3;
    public boolean isParkingFull;
    ParkingLotInformationSubscriber parkingLotInformationSubscriber;
    ParkingLotAttendant attendant = new ParkingLotAttendant();
    HashMap<Integer, Object> lotMap;

    //CONSTRUCTOR
    public ParkingLotSystem() {
        parkingLotInformationSubscriber = new ParkingLotInformationSubscriber();
    }

    //METHOD TO PARK THE VEHICLE
    public boolean isPark(Object vehicleToPark) throws ParkingLotException {
        if (this.vehicle.isEmpty()) {
            vehicle.add(vehicleToPark);
            return true;
        } else if (!this.vehicle.isEmpty() && (!this.vehicle.contains(vehicleToPark)) && (this.vehicle.size() < parkingLotCapacity)) {
            vehicle.add(vehicleToPark);
            return true;
        } else isParkingFull = true;
        parkingLotInformationSubscriber.notifyParkingStatus(true);
        return true;
    }

    //METHOD TO UNPARK THE GIVEN VEHICLE
    public boolean unParkTheVehicle(Object vehicleToUnPark) throws ParkingLotException {
        if (vehicle.contains(vehicleToUnPark)) {
            vehicle.remove(vehicleToUnPark);
            parkingLotInformationSubscriber.notifyParkingStatus(false);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_PARK_HERE, "Vehicle Is Not Parked Here");
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return false;
    }
}
