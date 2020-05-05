package com.bridgelabz;

public class ParkingLotException extends Exception {

    public ExceptionType type;

    public ParkingLotException(ExceptionType type,String message){
        super(message);
        this.type=type;
    }

    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE,VEHICLE_NOT_PARK_HERE;
    }
}
