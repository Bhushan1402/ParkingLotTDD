package com.bridgelabz;

public class Vehicle {

    DriverType driverType;
    ParkingDateTime parkingDateTime;
    private String parkingDateAndTime;
    private String vehicleId;

    public Vehicle(String vehicleId, DriverType driverType) {
        parkingDateTime = new ParkingDateTime();
        this.vehicleId = vehicleId;
        parkingDateAndTime = parkingDateTime.getDateTime();
        this.driverType = driverType;
    }

    public String getParkingDateAndTime() {
        return parkingDateAndTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public enum DriverType {HANDICAP, NORMAL}
}
