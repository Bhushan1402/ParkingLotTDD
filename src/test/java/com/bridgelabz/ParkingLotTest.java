package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    @Test
    public void givenAVehicleToPArk_WhenParkedInParkingLot_ShouldReturnTrue() {
        Object vehicle = new Object();
        boolean park = parkingLotSystem.isPark(vehicle);
        Assert.assertTrue(park);
    }
}
