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
        String key = (vehicle.vehicleSize == Vehicle.VehicleSize.LARGE) ? getSlotPositionWithLessNumberOfVehicleParked() : getParkingPosition();

        parkingLotSystem.vehicleMap.put(key, vehicle);
    }

        public void unParkedVehicle(Vehicle vehicle) throws ParkingLotException, ParkingLotException {
            if (parkingLotSystem.vehicleMap.containsValue(vehicle))
                parkingLotSystem.vehicleMap.remove(getVehiclePosition(vehicle), vehicle);
            else
                throw new ParkingLotException(ParkingLotException.ExceptionType.NOT_PARKED_HERE, "VEHICLE_NOT_PARKED_HERE");
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
            for (int index = 1; index <= parkingLotSystem.SIZE_OF_PARKING_LOT; index++) {
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

    public String getSlotPositionWithLessNumberOfVehicleParked() throws ParkingLotException {
        int count = 0;
        while (count++ < parkingLotSystem.NUMBER_OF_PARKING_LOTS) {
            if (parkingLotSystem.getNumberOfVehiclesParked(count) < (parkingLotSystem.SIZE_OF_PARKING_LOT - 1)) {
                return "A" + count + " " + (parkingLotSystem.getNumberOfVehiclesParked(count) + 1);
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Large Vehicles Can't Be Parked");
    }
}


