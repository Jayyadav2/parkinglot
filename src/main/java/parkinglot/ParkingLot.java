package parkinglot;

import java.util.concurrent.ConcurrentHashMap;
import  java.util.*;
public class ParkingLot {

    private List<Floor> floors;
    private CostStrategy costStrategy;
    private Map<String, Long> parkingTokens;

    public ParkingLot(int numFloors, Map<String, Integer> capacityByType) {
        floors = new ArrayList<>();
        for (int i = 1; i <= numFloors; i++) {
            floors.add(new Floor(i, capacityByType));
        }
        parkingTokens = new ConcurrentHashMap<>();
    }

    public void configureCostStrategy(Map<String, Integer> costPerHour) {
        this.costStrategy = new CostStrategy(costPerHour);
    }

    public String addVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            if (floor.parkVehicle(vehicle)) {
                String token = UUID.randomUUID().toString();
                parkingTokens.put(token, System.currentTimeMillis());
                return token;
            }
        }
        throw new RuntimeException("Parking lot is full for vehicle type: " + vehicle.getType());
    }

    public Vehicle removeVehicle(String token) {
        Long entryTime = parkingTokens.get(token);
        if (entryTime == null) {
            throw new RuntimeException("Invalid token");
        }
        for (Floor floor : floors) {
            for (List<VehicleSpace> spaces : floor.getSpacesByType().values()) {
                for (VehicleSpace space : spaces) {
                    Vehicle vehicle = space.getParkedVehicle();
                    if (vehicle != null && parkingTokens.containsKey(token)) {
                        long duration = (System.currentTimeMillis() - entryTime) / (1000 * 60 * 60);
                        int cost = costStrategy.calculateCost(vehicle.getType(), duration);
                        System.out.println("Parking cost: " + cost);
                        parkingTokens.remove(token);
                        return space.removeVehicle();
                    }
                }
            }
        }
        throw new RuntimeException("Vehicle not found for the given token");

    }

    public int checkAvailability(int floorNumber, String vehicleType) {
        return floors.get(floorNumber - 1).getAvailableSpaces(vehicleType);
    }

    public void displayStatus() {
        for (Floor floor : floors) {
            System.out.println("Floor " + floor);
            floor.getStatus().forEach((type, occupied) -> {
                System.out.println(type + ": " + occupied);
            });
        }
    }

}
