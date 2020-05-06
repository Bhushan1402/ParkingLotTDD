package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingOwner parkingLotOwner = null;
    AirportSecurity airportSecurity = null;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = new Date();

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(3);
        parkingLotOwner = new ParkingOwner();
        airportSecurity = new AirportSecurity();
    }


    @Test
    public void givenAVehicleToPArk_WhenParkedInParkingLot_ShouldReturnTrue() throws ParkingLotException {
        vehicle = new Vehicle("1");
        parkingLotSystem.isPark(vehicle);
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertTrue(isPark);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromParkingLot_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("1");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicle1);
        Assert.assertTrue(isUnPark);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedInParkingLot_ShouldThrowException() throws ParkingLotException {
        parkingLotSystem = new ParkingLotSystem(2);
        try {
            Object vehicleOne = new Object();
            parkingLotSystem.park(new Vehicle("1"));
            parkingLotSystem.park(new Vehicle("1"));
            parkingLotSystem.park(new Vehicle("3"));
            boolean park = parkingLotSystem.isPark(vehicleOne);
            Assert.assertTrue(park);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNoVehicleUnParked_ShouldThrowException() {
        try {

            Vehicle vehicle1 = new Vehicle("2");
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.isVehicleUnPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PARKED_HERE, e.type);
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

    @Test
    public void givenAVehicle_WhenNotParkedInParkingLot_ShouldReturnFalse() {
        vehicle = new Vehicle("1");
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertFalse(isPark);
    }

    @Test
    public void givenAVehicleToPark_WhenParkingLotOwnerGenerateNumber_AttendantShouldParkTheCar() {
        ParkingOwner parkingLotOwner = new ParkingOwner();
        HashMap parkingLotMap = null;
        Integer key = parkingLotOwner.generateParkingSlotNumber();
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        Object vehicle = new Object();
        boolean park;
        try {
            park = parkingLotAttendant.isPark(key, vehicle);
            Assert.assertTrue(park);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicleToPark_WhenPassingSameKeyToUnParkToAttendant_ShouldUnParkTheVehicle() {
        HashMap parkingLotMap = null;
        ParkingOwner parkingLotOwner = new ParkingOwner();
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        Integer key = parkingLotOwner.generateParkingSlotNumber();
        Object vehicle = new Object();
        boolean unPark;
        boolean park;
        try {
            park = parkingLotAttendant.isPark(key, vehicle);
            Assert.assertTrue(park);
            unPark = parkingLotAttendant.unParkTheVehicle(key);
            Assert.assertTrue(unPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicleToUnPark_WhenDriverCollectsTheKey_ShouldUnParkTheVehicle() {
        ParkingOwner parkingLotOwner = new ParkingOwner();
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        Driver driver = new Driver();
        Integer key = parkingLotOwner.generateParkingSlotNumber();
        Object vehicle = new Object();
        boolean unPark;
        boolean park;
        try {
            park = parkingLotAttendant.isPark(key, vehicle);
            Assert.assertTrue(park);
            unPark = driver.unParkTheVehicle(key);
            Assert.assertTrue(unPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }


}
