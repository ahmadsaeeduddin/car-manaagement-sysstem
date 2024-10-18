package system;
public class RegularRenter extends Renter {
    public RegularRenter(int renterId, String name, String email, String phoneNumber, String address) {
        super(renterId, name, email, phoneNumber, address);
    }

    @Override
    public double getDiscountRate() {
        return 1.0; // No discount
    }

    @Override
    public String renter_type() {
        return "Regular";
    }

    @Override
    public void print_details() {
        System.out.println(
                getRenterDetails() + ", Discount Rate: " + getDiscountRate() + ", Renter Type: " + renter_type());
        if (getRentedCars().isEmpty()) {
            System.out.println("No cars Rented Yet .... ");
        } else {
            for (Car c : getRentedCars()) {
                System.out.println(c.getBrand() + ": " + c.getModel() + ": " + c.getBaseFee() + ": ");
                System.out.println("================================");
                // c.getDetails();
            }
        }
    }
}