package system;
public class CorporateRenter extends Renter {
    public CorporateRenter(int renterId, String name, String email, String phoneNumber, String address) {
        super(renterId, name, email, phoneNumber, address);
    }

    @Override
    public double getDiscountRate() {
        return 0.8; // 20% discount
    }

    @Override
    public String renter_type() {
        return "Corporate";
    }

    @Override
    public void print_details() {
        System.out.println(
                getRenterDetails() + ", Discount Rate: " + getDiscountRate() + ", Renter Type: " + renter_type());
        if (getRentedCars().isEmpty()) {
            System.out.println("No cars Rented Yet .... ");
        } else {
            for (Car c : getRentedCars()) {
                System.out.println("ID: " + c.getCarId() + "Brand: " + c.getBrand() + "\nModel: " + c.getModel());
                System.out.println("================================");
                // c.getDetails();
            }
        }
    }
}