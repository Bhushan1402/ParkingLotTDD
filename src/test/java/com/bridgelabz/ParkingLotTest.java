package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingOwner parkingLotOwner = null;
    AirportSecurity airportSecurity = null;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = new Date();

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(5,5);
        parkingLotOwner = new ParkingOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicleToPark_WhenParkedInParkingLot_ShouldReturnTrue() {
        try {
            vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
            parkingLotSystem.park(vehicle);
            boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedInParkingLot_ShouldThrowException() {
        parkingLotSystem = new ParkingLotSystem(3,3);
        try {
            parkingLotSystem.park(new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE));
            parkingLotSystem.park(new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE));
            parkingLotSystem.park(new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNotParkedInParkingLot_ShouldReturnFalse() {
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL);
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertFalse(isPark);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromParkingLot_ShouldReturnTrue() {
        try {
            Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle1);
            boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicle1);
            Assert.assertTrue(isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenNoVehicleUnParked_ShouldThrowException() {
        try {
            Vehicle vehicle1 = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.isVehicleUnPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PARKED_HERE, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNotUnParked_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicle1);
            boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicle1);
            Assert.assertFalse(isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenThereIsSpace1inParkingLot_ShouldAllowToPark() {
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicle2 = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicle3 = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.unPark(vehicle1);
            parkingLotSystem.park(vehicle3);
            boolean isPark = parkingLotSystem.isVehicleParked(vehicle3);
            Assert.assertTrue(isPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAskForPosition_ShouldReturnPosition() {
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicle1);
            String vehiclePosition = parkingLotSystem.getVehiclePosition(vehicle1);
            Assert.assertEquals("A1 1", vehiclePosition);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenToPark_ShouldReturnParkingDateTime() {
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicle);
            String dateTime = formatter.format(date);
            String parkingDateAndTime = vehicle.getParkingDateAndTime();
            Assert.assertEquals(dateTime, parkingDateAndTime);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicles_WhenParkedInParkingLot_ShouldEvenlyDistributeInParkingLot() {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            String vehicle1Position = parkingLotSystem.getVehiclePosition(vehicleOne);
            String vehicle2Position = parkingLotSystem.getVehiclePosition(vehicleTwo);
            Assert.assertEquals("A1 1", vehicle1Position);
            Assert.assertEquals("A2 1", vehicle2Position);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenDriverIsHandicap_ShouldParkVehicleAtNearestLotPosition() {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.unPark(vehicleOne);
            parkingLotSystem.park(vehicleThree);
            String vehicle1Position = parkingLotSystem.getVehiclePosition(vehicleThree);
            Assert.assertEquals("A3 1", vehicle1Position);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleOfLargeSize_WhenMaximumNumberOfParkingSlotAvailable_ShouldParkedInThatParkingLot() {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleFour = new Vehicle("4", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            parkingLotSystem.park(vehicleFour);
            String vehicleFourPosition = parkingLotSystem.getVehiclePosition(vehicleFour);
            Assert.assertEquals("A4 1", vehicleFourPosition);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotMap_WhenNeedInformationAsPerVehicleColour_ShouldReturnVehicleMapWithRespectiveColour() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE);
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            Map<String, Vehicle> vehiclesOfColour = policeDepartment.getVehicles(Vehicle.VehicleColour.WHITE);
            Assert.assertEquals(3, vehiclesOfColour.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

}