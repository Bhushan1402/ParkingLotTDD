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
        boolean unPark = parkingLotSystem.unParkTheVehicle(vehicle);
        Assert.assertTrue(unPark);
    }

    @Test
    public void givenAVehicleToPark_WhenThereAreOtherVehicles_ShouldReturnTrue() throws ParkingLotException {
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLotSystem.isPark(vehicleOne);
        boolean park = parkingLotSystem.isPark(vehicleTwo);
        Assert.assertTrue(park);
    }

    @Test
    public void givenAVehicle_WhenNotPresentInParkingLot_ShouldReturnException() throws ParkingLotException {
        Object vehicleOne = new Object();
        Object vehicle = new Object();
        parkingLotSystem.isPark(vehicleOne);
        boolean unPark = false;
        try {
            unPark = parkingLotSystem.unParkTheVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Vehicle Is Not Parked Here",e.getMessage());
        }
    }
}
