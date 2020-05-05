package com.bridgelabz;

public class ParkingOwner implements IParkingLotObserver {

    //FIELDS
    boolean isParkingFull;
    ParkingLotSystem parkingLotSystem;

    @Override
    public void updateParkingLotStatus(boolean parkingLotStatus) {
        parkingLotSystem = new ParkingLotSystem();
        if (parkingLotStatus == true) {
            this.isParkingFull = parkingLotStatus;
            System.out.println("PARKING LOT OWNER:PARKING_LOT_IS_FULL");
        } else
            System.out.println("PARKING LOT OWNER:PARKING_LOT_IS_AVAILABLE");
            parkingLotSystem.isParkingFull = false;
    }
}
