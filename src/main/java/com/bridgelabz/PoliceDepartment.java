package com.bridgelabz;

import java.util.Map;
import java.util.stream.Collectors;

public class PoliceDepartment {

    public ParkingLotSystem parkingLotSystem;
    public Map<String, Vehicle> vehicles;
    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Map<String, Vehicle> getVehicles(Vehicle.VehicleColour white) {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .filter(entry -> colour.equals(entry.getValue().vehicleColour))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
