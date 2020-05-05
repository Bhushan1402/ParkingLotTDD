package com.bridgelabz;

public class ParkingLotException extends Exception {

    public ExceptionType type;

    public ParkingLotException(String message){
        super(message);
    }


    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE;
    }
}
