package parkinglot;

public class VehicleSpace {

    private int spaceNumber;
    private boolean isAvailable;
    private String vehicleType;
    private Vehicle parkedVehicle;

    public VehicleSpace(int spaceNumber, String vehicleType) {
        this.spaceNumber = spaceNumber;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    public Vehicle removeVehicle() {
        Vehicle vehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        this.isAvailable = true;
        return vehicle;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }
}
