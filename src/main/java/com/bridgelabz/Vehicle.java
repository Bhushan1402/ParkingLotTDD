package com.bridgelabz;

public class Vehicle {
    String attendantName;
    VehicleColour vehicleColour;
    VehicleSize vehicleSize;
    DriverType driverType;
    ParkingDateTime parkingDateTime;
    private String parkingDateAndTime;
    private String vehicleId;
    VehicleModel vehicleModel;

    public Vehicle(String vehicleId, DriverType driverType, VehicleSize vehicleSize, VehicleColour vehicleColour,VehicleModel vehicleModel,String attendantName) {
        parkingDateTime = new ParkingDateTime();
        this.vehicleId = vehicleId;
        parkingDateAndTime = parkingDateTime.getDateTime();
        this.driverType = driverType;
        this.vehicleSize = vehicleSize;
        this.vehicleColour = vehicleColour;
    }

    public String getParkingDateAndTime() {
        return parkingDateAndTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public enum DriverType {HANDICAP, NORMAL}
    public enum VehicleSize {LARGE, SMALL}
    public enum VehicleColour {WHITE, BLUE}
    public enum VehicleModel {BMW, TOYOTA}
}
