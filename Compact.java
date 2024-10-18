package system;

// ! ---------------------- [ Compact Car ] -------------------------//
public class Compact extends Car {
    public Compact(int carId, String brand, String model, String year, double base_fee, String plateNumber) {
        super(carId, brand, model, year, base_fee, plateNumber);

    }

    @Override
    public double calculateRent(double distanceTravelled) {
        return getBaseFee() + 0.10 * distanceTravelled;
    }

    @Override
    public boolean isInsurable() {
        return false;
    }

    @Override
    public double insu_tax(){
        return 1;
    }

    @Override
    public String type() {
        return "Compact";
    }

    @Override
    public double damagecost(){
        return 0.12;
    }

    @Override
    public String getDetails() {
        return "ID: " + getCarId() + "\nBrand: " + getBrand() + "\nModel: " + getModel() + " (" + getYear() + ")"
                + "\nType: " + type()
                + "\nBase Price: " + getBaseFee()
                + "\nInsurance: " +
                (isInsurable() ? "Yes" : "No");
    }
}