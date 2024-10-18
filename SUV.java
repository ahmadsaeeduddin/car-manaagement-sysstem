package system;
// ! ---------------------- [ SUV ] -------------------------//
// ! ---------------------- [ SUV ] -------------------------//

public class SUV extends Car {
   public SUV(int carId, String brand, String model, String year, double base_fee, String plateNumber) {
       super(carId, brand, model, year, base_fee, plateNumber);
   }

   @Override
   public double calculateRent(double distance_travel) {
       return getBaseFee() + 0.18 * distance_travel;
   }

   @Override
   public boolean isInsurable() {
       return true;
   }

   @Override
   public String type() {
       return "SUV";
   }

   @Override
   public double damagecost() {
       return 0.29;
   }

   @Override
   public double insu_tax(){
       return getBaseFee() * 0.22;
   }

   @Override
   public String getDetails() {
       return "\nID: " + getCarId()
               + "\nBrand: " + getBrand()
               + "\nModel: " + getModel() + " (" + getYear() + ")"
               + "\nType: " + type()
               + "\nBase Price: " + getBaseFee()
               + "\nInsurance: " +
               (isInsurable() ? String.valueOf(insu_tax()) : "No")
               + "\nDamage cost: " + String.valueOf(damagecost() * 100) + " %";
   }
}