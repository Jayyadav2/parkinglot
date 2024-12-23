package parkinglot;

import java.util.Map;
import java.util.*;

// Floor class
public class Floor {
    private int floorNumber;
    private Map<String, List<VehicleSpace>> spacesByType;

    public Floor(int floorNumber, Map<String, Integer> capacityByType) {
        this.floorNumber = floorNumber;
        this.spacesByType = new HashMap<>();
        capacityByType.forEach((type, capacity) -> {
            List<VehicleSpace> spaces = new ArrayList<>();
            for (int i = 1; i <= capacity; i++) {
                spaces.add(new VehicleSpace(i, type));
            }
            spacesByType.put(type, spaces);
        });
    }

    // Getter method to provide access to spacesByType
    public Map<String, List<VehicleSpace>> getSpacesByType() {
        return spacesByType;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        List<VehicleSpace> spaces = spacesByType.get(vehicle.getType());
        if (spaces != null) {
            for (VehicleSpace space : spaces) {
                if (space.isAvailable()) {
                    space.parkVehicle(vehicle);
                    return true;
                }
            }
        }
        return false;
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (List<VehicleSpace> spaces : spacesByType.values()) {
            for (VehicleSpace space : spaces) {
                if (!space.isAvailable() && space.getParkedVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space.removeVehicle();
                }
            }
        }
        return null;
    }

    public int getAvailableSpaces(String vehicleType) {
        return (int) spacesByType.getOrDefault(vehicleType, Collections.emptyList())
                .stream()
                .filter(VehicleSpace::isAvailable)
                .count();
    }

    public Map<String, Integer> getStatus() {
        Map<String, Integer> status = new HashMap<>();
        spacesByType.forEach((type, spaces) -> {
            int occupied = (int) spaces.stream().filter(space -> !space.isAvailable()).count();
            status.put(type, occupied);
        });
        return status;
    }
}

