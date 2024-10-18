package system;

import java.util.ArrayList;
import java.util.List;

public abstract class Renter {
    private int renterId;
    private String name;
    private String email;
    private List<Car> rentedCars;
    private double total_fee;
    private String phoneNumber;
    private String address;

    public Renter(int renterId, String name, String email, String phoneNumber, String address) {
        this.renterId = renterId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rentedCars = new ArrayList<>();
        this.total_fee = 0.0;
    }

    public abstract double getDiscountRate();

    public abstract String renter_type();

    public abstract void print_details();

    public void addCar(Car car) {
        rentedCars.add(car);
        car.setRented(true);
    }

    public void removeCar(Car car) {
        rentedCars.remove(car);
        car.setRented(false);
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRenterDetails() {
        return renterId + ": " + name + ", Email: " + email;
    }

    public List<Car> getRentedCars() {
        return rentedCars;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}






