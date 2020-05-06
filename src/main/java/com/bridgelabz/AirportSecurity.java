package com.bridgelabz;

public class AirportSecurity implements IParkingLotObserver{
    //FIELD
    private boolean isParkingFull;

    @Override
    public void updateParkingLotStatus(boolean parkingLotStatus) {
        this.isParkingFull = parkingLotStatus;
    }
}
