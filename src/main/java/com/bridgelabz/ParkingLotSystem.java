package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    public static void main(String[] args) {
        System.out.println("Welcome To ParkingLot");
    }

    List<Object> vehicle = new ArrayList<Object>();

    //METHOD TO PARK THE VEHICLE
    public boolean isPark(Object vehicleToPark) throws ParkingLotException {
        if (this.vehicle.isEmpty()) {
            vehicle.add(vehicleToPark);
            return true;
        } else if (!this.vehicle.isEmpty() && (!this.vehicle.contains(vehicleToPark)) && (this.vehicle.size() < 5)) {
            vehicle.add(vehicleToPark);
            return true;
        } else
            throw new ParkingLotException("Parking Lot Is Full");
    }

    //METHOD TO UNPARK THE GIVEN VEHICLE
    public boolean unParkTheVehicle(Object vehicleToUnPark) throws ParkingLotException {
        if (vehicle.contains(vehicleToUnPark)) {
            vehicle.remove(vehicleToUnPark);
            return true;
        }
        throw new ParkingLotException("Vehicle Is Not Parked Here");
    }
}
