package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    @Test
    public void givenAVehicleToPArk_WhenParkedInParkingLot_ShouldReturnTrue() throws ParkingLotException {
        Object vehicle = new Object();
        boolean park = parkingLotSystem.isPark(vehicle);
        Assert.assertTrue(park);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromParkingLot_ShouldReturnTrue() throws ParkingLotException {
        Object vehicle = new Object();
        parkingLotSystem.isPark(vehicle);
        boolean unPark = parkingLotSystem.unParkTheVehicle(vehicle);
        Assert.assertTrue(unPark);
    }

    @Test
    public void givenAVehicleToPark_WhenThereAreOtherVehicles_ShouldReturnTrue() throws ParkingLotException {
        Object vehicleOne = new Object();
        boolean park = parkingLotSystem.isPark(vehicleOne);
        Assert.assertTrue(park);
    }

    @Test
    public void givenAVehicle_WhenNotPresentInParkingLot_ShouldReturnException() {
        Object vehicleOne = new Object();
        Object vehicle = new Object();
        try {
            parkingLotSystem.isPark(vehicleOne);
            parkingLotSystem.unParkTheVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Vehicle Is Not Parked Here",e.getMessage());
        }
    }

    @Test
    public void givenAVehicleToPark_WhenParkingLotIsFull_ShouldInformAuthorities() {
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        try {
            parkingLotSystem.isPark(vehicleOne);
            parkingLotSystem.isPark(vehicleTwo);
            parkingLotSystem.isPark(vehicleThree);
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking Lot Is Full",e.getMessage());
        }
    }

    @Test
    public void givenAVehicleToPark_WhenParkingLotIsFull_ShouldInformOwnerAndSecurity() {
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        try {
            parkingLotSystem.isPark(vehicleOne);
            parkingLotSystem.isPark(vehicleTwo);
            parkingLotSystem.isPark(vehicleThree);
            parkingLotSystem.unParkTheVehicle(vehicleOne);
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking Lot Is Full",e.getMessage());
        }
    }
}
