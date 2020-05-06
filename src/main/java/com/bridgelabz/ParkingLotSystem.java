package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {
    int SIZE_OF_LOT;
    ParkingLotManager parkingLotManager;
    ParkingOwner parkingOwner;
    AirportSecurity airportSecurity;
    ParkingLotAttendant parkingLotAttendant;
    Map<String, Vehicle> vehicleMap;

    //CONSTRUCTOR
    public ParkingLotSystem(int SIZE_OF_LOT) {
        this.SIZE_OF_LOT = SIZE_OF_LOT;
        parkingLotManager = new ParkingLotManager();
        parkingOwner = new ParkingOwner();
        airportSecurity = new AirportSecurity();
        parkingLotManager.addObserver(parkingOwner);
        parkingLotManager.addObserver(airportSecurity);
        parkingLotAttendant = new ParkingLotAttendant(this);
        vehicleMap = new HashMap<>();
    }

    public ParkingLotSystem() {
    }

    //METHOD TO PARK VEHICLE
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (isLotFull()) {
            parkingLotManager.notifyParkingStatus(true);
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking is full");
        }
        parkingLotAttendant.parkVehicle(vehicle);
    }

    //METHOD TO CHECK LOT IS FULL OR NOT
    public boolean isLotFull() {
        return vehicleMap.size() == SIZE_OF_LOT;
    }

    //METHOD TO UNPARK VEHICLE
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (!isVehicleParked(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.NOT_PARKED_HERE, "NO vehicle");
        }
        parkingLotAttendant.unParkedVehicle(vehicle);
        parkingLotManager.notifyParkingStatus(false);
    }

    //METHOD TO CHECK VEHICLE PARKED OR NOT
    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicleMap.containsValue(vehicle);
    }

    //METHOD TO CHECK VEHICLE UNPARKED
    public boolean isVehicleUnPark(Vehicle vehicle) {
        return !isVehicleParked(vehicle);
    }

    //METHOD TO GET VEHICLE POSITION
    public String getVehiclePosition(Vehicle vehicle) {
        return parkingLotAttendant.getVehiclePosition(vehicle);
    }
}
