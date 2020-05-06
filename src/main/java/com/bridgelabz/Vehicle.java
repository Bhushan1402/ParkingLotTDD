package com.bridgelabz;

public class Vehicle {
    VehicleSize vehicleSize;
    DriverType driverType;
    ParkingDateTime parkingDateTime;
    private String parkingDateAndTime;
    private String vehicleId;

    public Vehicle(String vehicleId, DriverType driverType, VehicleSize vehicleSize) {
        parkingDateTime = new ParkingDateTime();
        this.vehicleId = vehicleId;
        parkingDateAndTime = parkingDateTime.getDateTime();
        this.driverType = driverType;
        this.vehicleSize = vehicleSize;
    }

    public String getParkingDateAndTime() {
        return parkingDateAndTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public enum DriverType {HANDICAP, NORMAL}
    public enum VehicleSize {LARGE, SMALL}
}
