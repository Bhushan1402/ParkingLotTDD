package com.bridgelabz;

import java.util.HashMap;

public class ParkingOwner implements IParkingLotObserver {

    //FIELDS
    boolean isParkingFull;
    ParkingLotSystem parkingLotSystem;

    public ParkingOwner() {
        parkingLotSystem = new ParkingLotSystem();
    }


    @Override
    public void updateParkingLotStatus(boolean parkingLotStatus) {
        isParkingFull = parkingLotStatus;
    }
}
