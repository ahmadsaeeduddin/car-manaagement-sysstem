package system;
//! ---------------------- [ Luxury Car ] -------------------------//
public class LuxuryCar extends Car {
 public LuxuryCar(int carId, String brand, String model, String year, double base_fee, String plateNumber) {
     super(carId, brand, model, year, base_fee, plateNumber);
 }

 @Override
 public double calculateRent(double distanceTravelled) {
     return getBaseFee() + 0.30 * distanceTravelled + 20;
 }

 @Override
 public boolean isInsurable() {
     return true;
 }

 @Override
 public String type() {
     return "Luxury";
 }

 @Override
 public double damagecost() {
     return 0.44;
 }

 @Override
 public double insu_tax(){
     return getBaseFee() * 0.44;
 }

 @Override
 public String getDetails() {
     return "ID: " + getCarId() + "\nBrand: " + getBrand() + "\nModel: " + getModel() + " (" + getYear() + ")"
             + "\nType: " + type()
             + "\nBase Price: " + getBaseFee()
             + "\nInsurance: " +
             (isInsurable() ? "$ " + String.valueOf(insu_tax()) : "No") + "\nDamage cost: " + String.valueOf(damagecost() * 100) + "%";
 }
}