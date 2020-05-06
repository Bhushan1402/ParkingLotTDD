package com.bridgelabz;

public interface IParkingLotInFormation {
    void notifyParkingStatus(boolean status);
    void addObserver(IParkingLotObserver lotObserver);
}
