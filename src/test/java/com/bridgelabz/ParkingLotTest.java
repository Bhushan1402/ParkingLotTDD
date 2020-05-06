package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingOwner parkingLotOwner = null;
    AirportSecurity airportSecurity = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.now();
    String formattedDateTime = formatter.format(dateTime);
    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(5,5);
        parkingLotOwner = new ParkingOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicleToPark_WhenParkedInParkingLot_ShouldReturnTrue() {
        try {
            vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
            parkingLotSystem.park(new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy"));
            parkingLotSystem.park(new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy"));
            parkingLotSystem.park(new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy"));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNotParkedInParkingLot_ShouldReturnFalse() {
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertFalse(isPark);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromParkingLot_ShouldReturnTrue() {
        try {
            Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
            Vehicle vehicle1 = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.isVehicleUnPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PARKED_HERE, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNotUnParked_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicle2 = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicle3 = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAskForPosition_ShouldReturnPosition() {
        Vehicle vehicle1 = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        try {
            parkingLotSystem.park(vehicle);
            LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime, formatter);
            LocalDateTime parkingDateAndTime = vehicle.parkingDateAndTime;
            Assert.assertEquals(dateTime, parkingDateAndTime);
            Assert.assertEquals(dateTime, parkingDateAndTime);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicles_WhenParkedInParkingLot_ShouldEvenlyDistributeInParkingLot() {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleFour = new Vehicle("4", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            parkingLotSystem.park(vehicleFour);
            String vehicleFourPosition = parkingLotSystem.getVehiclePosition(vehicleFour);
            Assert.assertEquals("A1 2", vehicleFourPosition);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotMap_WhenNeedInformationAsPerVehicleColour_ShouldReturnVehicleMapWithRespectiveColour() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Roy");
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

    @Test
    public void givenParkingLot_WhenVehicleColorAndBrandMatch_ShouldReturnVehicleListWithColorBlueAndModelToyota() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.TOYOTA, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Joy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "B");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            Map<String, Vehicle> vehiclesList = policeDepartment.getVehiclesWithColorAndModel(Vehicle.VehicleColour.BLUE,
                    Vehicle.VehicleModel.TOYOTA);
            Assert.assertEquals(1, vehiclesList.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenNeedVehicleOfParticularModel_ShouldReturnVehiclesInformationToConcernParty() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "Joy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.TOYOTA, "Roy");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            Map<String, Vehicle> vehiclesOfParticularModel = policeDepartment.getVehiclesOfModel(Vehicle.VehicleModel.BMW);
            Assert.assertEquals(2, vehiclesOfParticularModel.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleParkedInLastHalfHourMinutes_ShouldReturnVehiclesList() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "Joy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Joy");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            Map<String, Vehicle> vehiclesParkedInLastHalfHour = policeDepartment.getVehiclesParkedFromLastMinutes(30);
            Assert.assertEquals(3, vehiclesParkedInLastHalfHour.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenNeedInformationOfAllVehicles_ShouldReturnVehiclesList() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "Roy");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "Joy");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "Joy");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            Map<String, Vehicle> allVehicles = policeDepartment.getAllParkedVehicles();
            Assert.assertEquals(3, allVehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}