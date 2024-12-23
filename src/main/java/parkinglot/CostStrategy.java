package parkinglot;

import java.util.*;
public class CostStrategy {
    private Map<String, Integer> costPerHour;

    public CostStrategy(Map<String, Integer> costPerHour) {
        this.costPerHour = costPerHour;
    }

    public int calculateCost(String vehicleType, long durationHours) {
        return (int) (costPerHour.getOrDefault(vehicleType, 0) * durationHours);
    }
}
