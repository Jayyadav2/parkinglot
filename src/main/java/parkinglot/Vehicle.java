package parkinglot;


public class Vehicle {

    private String type;
    private String registrationNumber;
    private String color;

    public Vehicle(String type, String registrationNumber, String color) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}
