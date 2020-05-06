package com.bridgelabz;

import java.util.HashMap;

public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem;
    private int lot;
    int character = 65;
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
        int slotNumber = 0;

        while (slotNumber++ <= parkingLotSystem.SIZE_OF_PARKING_LOT) {
            char row = (char) (character + slotNumber);
            int flag = 0;
            for (int lotNumber = 1; lotNumber <= parkingLotSystem.NUMBER_OF_PARKING_LOTS; lotNumber++) {
                String key = lotNumber + "A" + row + " " + slotNumber;
                if (!parkingLotSystem.vehicleMap.containsKey(key)) {
                    position = key;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
            break;
        }
        return position;
    }

    public String getSlotPositionWithLessNumberOfVehicleParked() throws ParkingLotException {
        int count = 0;
        int character = 65;
        while (count++ < parkingLotSystem.NUMBER_OF_PARKING_LOTS) {
            int numberOfVehicles = parkingLotSystem.getNumberOfVehiclesParked(count);
            char row = (char) (character + numberOfVehicles);
            if (parkingLotSystem.getNumberOfVehiclesParked(count) < (parkingLotSystem.SIZE_OF_PARKING_LOT - 1)) {
                return count + "A" + row + " " + (parkingLotSystem.getNumberOfVehiclesParked(count) + 1);
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Large Vehicles Can't Be Parked");
    }
}


