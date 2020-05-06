package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager implements IParkingLotInFormation {
    private List<IParkingLotObserver> lotObservers;

    public ParkingLotManager() {
        lotObservers = new ArrayList<>();
    }

    @Override
    public void addObserver(IParkingLotObserver lotObserver) {
        lotObservers.add(lotObserver);
    }

    @Override
    public void notifyParkingStatus(boolean parkingStatus) {
        for (IParkingLotObserver lotObserver : lotObservers) {
            lotObserver.updateParkingLotStatus(parkingStatus);
        }
    }
}
