package com.bridgelabz;

public class AirportSecurity implements IParkingLotObserver{
    //FIELD
    private boolean isParkingFull;

    @Override
    public void updateParkingLotStatus(boolean parkingLotStatus) {
        if (this.isParkingFull = parkingLotStatus) {
            System.out.println("AIRPORT SECURITY:PARKING LOT IS FULL");
        } else
            System.out.println("AIRPORT SECURITY:PARKING LOT IS AVAILABLE");
    }
}
