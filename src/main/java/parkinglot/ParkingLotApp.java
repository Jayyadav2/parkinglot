package parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
public class ParkingLotApp {
    public static void main(String[] args) {
        Map<String, Integer> capacityByType = new HashMap<>();
        capacityByType.put("Car", 2);

        ParkingLot parkingLot = new ParkingLot(1, capacityByType);

        parkingLot.configureCostStrategy(Map.of(
                "Car", 20
        ));

        String token1 = parkingLot.addVehicle(new Vehicle("Car", "ABC123", "Red"));
        String token2 = parkingLot.addVehicle(new Vehicle("Car", "DEF456", "Blue"));

        parkingLot.displayStatus();

        parkingLot.removeVehicle(token1);
        parkingLot.displayStatus();

        try {
            parkingLot.addVehicle(new Vehicle("Car", "GHI789", "Green"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
