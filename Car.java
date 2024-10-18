package system;
public abstract class Car {
    private int carId;
    private String brand;
    private String model;
    private String year;
    private boolean isRented;
    private double base_fee;
    private String plateNumber;
    private double totalRent;

    public Car(int carId, String brand, String model, String year, double base_fee, String plateNumber) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isRented = false;
        this.base_fee = base_fee;
        this.plateNumber = plateNumber;
        this.totalRent = 0;
    }

    public abstract double calculateRent(double distanceTravelled);

    public abstract boolean isInsurable();

    public abstract String getDetails();

    public abstract String type();

    public abstract double damagecost();

    public abstract double insu_tax();

    public boolean isRented() {
        return isRented;
    }

    public double getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(double tot){
        totalRent = tot;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public String getYear() {
        return year;
    }

    public int getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    // Setter for brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter for model
    public String getModel() {
        return model;
    }

    // Setter for model
    public void setModel(String model) {
        this.model = model;
    }

    // Getter for base_fee
    public double getBaseFee() {
        return base_fee;
    }

    // Setter for base_fee
    public void setBaseFee(double base_fee) {
        this.base_fee = base_fee;
    }

    // Getter for plateNumber
    public String getPlateNumber() {
        return plateNumber;
    }

    // Setter for plateNumber
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
