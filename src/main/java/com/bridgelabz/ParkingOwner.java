package com.bridgelabz;

import java.util.HashMap;

public class ParkingOwner implements IParkingLotObserver {

    //FIELDS
    boolean isParkingFull;
    ParkingLotSystem parkingLotSystem;
    public HashMap<Integer, Object> parkingMap = new HashMap<Integer, Object>();


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

    public Integer generateParkingSlotNumber() {
        for (int i = 1; i <= 5; i++)
            if (this.parkingMap.get(i) == null)
                return i;
        return null;
    }
}
