package com.bridgelabz;

import java.util.HashMap;

public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem;
    private int lot;
    public ParkingLotAttendant(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public void parkVehicle(Vehicle vehicle) throws ParkingLotException {
        if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.ALREADY_PARKED, "Vehicle is already parked");
        }
        String key = getParkingPosition();
        parkingLotSystem.vehicleMap.put(key, vehicle);
    }

    public void unParkedVehicle(Vehicle vehicle) {
        parkingLotSystem.vehicleMap.entrySet().removeIf(entry -> vehicle.equals(entry.getValue()));
    }

    public String getVehiclePosition(Vehicle vehicle) {
        return parkingLotSystem.vehicleMap.keySet().stream()
                .filter(key -> vehicle.equals(parkingLotSystem.vehicleMap.get(key)))
                .findFirst()
                .get();
    }

    public String getParkingPosition() {
        String position = null;
        while (lot++ <= parkingLotSystem.NUMBER_OF_PARKING_LOTS) {
            for (int index = 1; index < parkingLotSystem.SIZE_OF_PARKING_LOT; index++) {
                String key = "A".concat(lot + " " + index);
                if (!parkingLotSystem.vehicleMap.containsKey(key)) {
                    position = key;
                    break;
                }
            }
            if (lot == parkingLotSystem.NUMBER_OF_PARKING_LOTS)
                lot = 0;
            break;
        }
        return position;
    }
}
