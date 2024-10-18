package system;

import java.util.ArrayList;
import java.util.Scanner;

public class CRMS {
    private ArrayList<Renter> renters;
    private ArrayList<Car> car_list;
    private counter count;
    private Scanner scanner;
    private ArrayList<Rent_Transactions> transactions;

    CRMS(ArrayList<Renter> r, ArrayList<Car> car, counter c) {
        renters = r;
        car_list = car;
        count = c;
        scanner = new Scanner(System.in);
        transactions = new ArrayList<Rent_Transactions>();
    }

    // ^ ------------------------ [ Admin Functionalities ] ---------------------- ^
    // //

    private void AvailableNonRentedCars() {
        System.out.println("\nAvailable Non-Rented Cars:");
        if (car_list.isEmpty()) {
            System.out.println("No cars available in this system.");
            return;
        }
        for (Car car : car_list) {
            if (!car.isRented()) {
                System.out.println(car.getDetails());
                System.out.println("============ [  ] ==========");
            }
        }
    }

    private void All_AvailableCars() {
        System.out.println("\nAll Available Cars Registered in this system:\n");
        if (car_list.isEmpty()) {
            System.out.println("No cars available in this system.");
            return;
        }
        for (Car car : car_list) {
            System.out.println(car.getDetails());
            System.out.println("================================");
        }
    }

    private void addingCarInSystem() {
        int carType = 0;
        do {
            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine(); // No changes here
            System.out.print("Enter Model: ");
            String model = scanner.nextLine(); // No changes here

            System.out.print("Enter Year: ");
            String year = scanner.nextLine(); // Read the year as string
            if (!isNumber(year) || year.length() != 4) { // Fix: Check if year is a number AND exactly 4 digits
                System.out.println("Invalid year format. Please enter a valid 4-digit year.");
                continue; // Re-prompt if invalid year
            }

            System.out.print("Enter Base Fee: ");
            String base_fee = scanner.nextLine(); // Read base fee as string
            if (!isNumber(base_fee)) { // Validate base fee
                System.out.println("Invalid base fee format. Please enter again.");
                continue; // Re-prompt if base fee is invalid
            }

            System.out.print("Enter Plate Number: ");
            String plateNumber = scanner.nextLine(); // No changes here

            // Display car type options and get user input
            System.out.println("Car Type\n1. Compact Car\n2. SUV Car\n3. Luxury Car");
            carType = scanner.nextInt();
            scanner.nextLine(); // Fix: Consume the leftover newline character after nextInt()

            switch (carType) {
                case 1:
                    Car car1 = new Compact(count.cars++, brand, model, year, Double.parseDouble(base_fee), plateNumber);
                    car_list.add(car1);
                    break;

                case 2:
                    Car car2 = new SUV(count.cars++, brand, model, year, Double.parseDouble(base_fee), plateNumber);
                    car_list.add(car2);
                    break;

                case 3:
                    Car car3 = new LuxuryCar(count.cars++, brand, model, year, Double.parseDouble(base_fee),
                            plateNumber);
                    car_list.add(car3);
                    break;

                default:
                    System.out.println("Invalid car type. Please try again.");
                    carType = 0;
                    break;
            }
        } while (carType == 0);

        System.out.println("Car is being added wait ......\n");
        System.out.println("Car added successfully !!");
    }

    private void addingRenterInSystem() {
        int renterType = 0;
        do {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            if (!isValidEmail(email)) { // Validating email format
                System.out.println("Invalid email format. Please enter again.");
                continue;
            }

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();
            if (!isPhoneNumberValid(phoneNumber)) { // Validating phone number
                System.out.println("Invalid phone number format. Please enter again.");
                continue;
            } else if (numberCheck(phoneNumber) == -1) {
                System.out.println("Phone number already exists. Please enter a different number.");
                continue;
            }

            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            // Display renter type options and get user input
            System.out
                    .println("Choose Renter Type:-\n\t1. Regular Renter\n\t2. Frequent Renter\n\t3. Corporate Renter");
            renterType = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character after nextInt()

            switch (renterType) {
                case 1:
                    Renter r1 = new RegularRenter(count.renters++, name, email, phoneNumber, address);
                    renters.add(r1);
                    break;

                case 2:
                    Renter r2 = new FrequentRenter(count.renters++, name, email, phoneNumber, address);
                    renters.add(r2);
                    break;

                case 3:
                    Renter r3 = new CorporateRenter(count.renters++, name, email, phoneNumber, address);
                    renters.add(r3);
                    break;

                default:
                    System.out.println("Invalid renter type. Please try again.");
                    renterType = 0;
                    break;
            }
        } while (renterType == 0);

        System.out.println("Renter is being added, please wait ......\n");
        System.out.println("Renter added successfully !!");
    }

    private int FindRenter_ID(int id, int i) {
        int index = 0;
        for (Renter renter : renters) {
            if (renter.getRenterId() == id) {
                if (i == 0) {
                    renter.print_details();
                }
                return index;
            }
            index++;
        }
        System.out.println("No renter found with this ID.");
        return -1;
    }

    private void All_Renter_inSystem() {
        System.out.println("\nAll Renters Registered in this system:\n");
        if (renters.isEmpty()) {
            System.out.println("No Renter in this system.");
            return;
        }
        for (Renter renter : renters) {
            renter.print_details();
            System.out.println("================================");
        }
    }

    private void removeCar(int car_id) {
    	if(FindCarByID(car_id) == -1) {
    		System.out.println("This ID is no allocated to any car !");
    		return ;
    	}
        for (Car car : car_list) {
            if (car.getCarId() == car_id) {
                if (car.isRented()) {
                    System.out.println("Car is currently rented, can't remove.");
                    return;
                }
                car_list.remove(car);
                System.out.println("Car removed successfully.");
                return;
            }
        }
    }

    private void removeRenter(int renter_id) {
        if (FindRenter_ID(renter_id, 1) == -1) {
            System.out.println("Can't remove renter. Renter not found with this ID.");
            return;
        }
        for (Renter renter : renters) {
            if (renter.getRenterId() == renter_id) {
                if (!renter.getRentedCars().isEmpty()) {
                    System.out.println("Renter has rented cars, can't remove.");
                    return;
                }
                renters.remove(renter);
                System.out.println("Renter removed successfully.");
                return;
            }
        }
    }

    // ^ = = = = = = = = = = = = = = = = = = [END] = = = = = = = = = = = = = = = = ^

    // * ------------------------- [ User Functionalities ] ---------------------- ^

    private void RentedCars() {
        System.out.println("\nRented Cars:");
        if (car_list.isEmpty()) {
            System.out.println("No cars available in this system.");
            return;
        }
        for (Car car : car_list) {
            if (car.isRented()) {
                System.out.println(car.getDetails());
                System.out.println("============ [ RENTED ] ==========");
            }
        }
    }

    private void rentCarToRenter() {
        System.out.println("Renters ID:");
        int renter_id = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character after nextInt()
        int index = FindRenter_ID(renter_id, 1);
        if (index == -1) {
            System.out.println("No renter found with this ID.");
            return;
        } else {
            System.out.println(
                    "Renter Name: " + renters.get(index).getName() + "\nType: " + renters.get(index).renter_type());
            System.out.println("\nAvailable Cars:");
            for (Car car : car_list) {
                if (!car.isRented()) {
                    System.out.println("Car Id: " + car.getCarId() + "\nModel: " + car.getModel() + "\nType: "
                            + car.type() + "\nBase Fee: " + car.getBaseFee());
                    if (!car.type().equals("Compact")) {
                        System.out.println("Insurance Also Applicable !");
                    }
                }
            }
            System.out.print("\nEnter Car ID to rent: ");
            int car_id = scanner.nextInt();
            scanner.nextLine();
            int i = FindCarByID(car_id);
            if (i == -1) {
                System.out.println("No car found with this ID.");
                return;
            } else {
                if (car_list.get(i).isInsurable()) {
                    System.out.println("Insurance is applicable, please confirm your rental (y/n): ");
                    String confirm = scanner.nextLine();
                    if (!confirm.equalsIgnoreCase("y")) {
                        System.out.println("Rental cancelled.");
                        return;
                    }
                    System.out.println("Transaction To Be Made Of : $ " + car_list.get(i).insu_tax()
                            + "\nPLease Confirm Your Payment (y/n): ");
                    String confirm1 = scanner.nextLine();
                    if (!confirm1.equalsIgnoreCase("y")) {
                        System.out.println("Rental cancelled.");
                        return;
                    }
                }
                car_list.get(i).setTotalRent(car_list.get(i).insu_tax());
                car_list.get(i).setRented(true);
                renters.get(index).addCar(car_list.get(i));
                System.out.println("Car rented successfully.");
            }
        }
    }

    private void RentedCarsBy(int id) {
        System.out.println("\nRented Cars by Renter with ID: " + id);
        if (renters.isEmpty()) {
            System.out.println("No renters registered in this system.");
            return;
        }
        for (Renter renter : renters) {
            if (renter.getRenterId() == id) {
                System.out.println("Renter Name: " + renter.getName());
                System.out.println("\nRented Cars:");
                for (Car car : renter.getRentedCars()) {
                    System.out.println(car.getDetails());
                }
                return;
            }
        }
    }

    private void ReturningCar() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        System.out.println("Enter Renter ID who is returning car: ");
        int renter_id = scanner.nextInt();
        scanner.nextLine();
        int index = FindRenter_ID(renter_id, 1);
        if (index == -1) {
            System.out.println("No renter found with this ID.");
            return;
        } else {
            System.out.println(
                    "Renter Name: " + renters.get(index).getName() + "\nType: " + renters.get(index).renter_type());
            if (renters.get(index).getRentedCars().isEmpty()) {
                System.out.println("No rented cars found.");
                return;
            }
            System.out.println("\nRented Cars:");
            for (Car car : renters.get(index).getRentedCars()) {
                System.out.println("ID: " + car.getCarId() + "    " + car.getBrand() + " -- " + car.getModel());
                temp.add(car.getCarId());
            }
        }
        System.out.println("Enter the Car ID of the car you want to return: ");
        int car_id = scanner.nextInt();
        scanner.nextLine();
        int i = -1;
        for (int j = 0; j < temp.size(); j++) {
            if (car_id == temp.get(j)) {
                i = 22;
            }
        }
        int OGlistID = FindCarByID(car_id);
        int carid1 = 0;
        for (Car c : renters.get(index).getRentedCars()) {
            if (c.getCarId() == car_id) {
                break;
            }
            carid1++;
        }

        if (i == -1) {
            System.out.println("No car found with this ID.");
            return;
        } else {
            if (!car_list.get(OGlistID).isRented()) {
                System.out.println("Car is not rented, can't return.");
                return;
            }
            // car_list.get(OGlistID).setRented(false);
            TransactionDetails(OGlistID, index);
            renters.get(index).removeCar(car_list.get(OGlistID));
        }
    }

    private void TransactionDetails(int car_id, int renter_id) {
        System.out.println("Enter How many KM The car has been Driven ? : ");
        double KM = scanner.nextDouble();
        scanner.nextLine();

        Rent_Transactions tran = new Rent_Transactions(car_list.get(car_id), renters.get(renter_id), KM);
        int damage;
        System.out.println("Is there any kind of damage (y/n) ? ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            System.out.println("Enter Damage Percentage (0-100): ");
            damage = scanner.nextInt();
            scanner.nextLine();
            double totalCost = tran.calculateTotalCost(1, damage);
            System.out.println("Car Returned, Waiting for payment...........");
            System.out.println("Total Cost: $" + totalCost);
            System.out.println("Payment Received ... Car Returned Successfull ....");

        } else {
            double totalCost = tran.calculateTotalCost(0, 0);
            System.out.println("Car Returned, Waiting for payment...........");
            System.out.println("Total Cost: $" + totalCost);
            System.out.println("Payment Received ... Car Returned Successfull ....");
        }
        transactions.add(tran);
    }

    public void start() {
        while (true) {
            System.out.println("\n ** Welcome to the Car Rental Management System **");
            System.out.println("\t1. Admin");
            System.out.println("\t2. Renter");
            System.out.println("\t0. Exit");
            System.out.print("Choose your role: ");
            String choice = scanner.nextLine();
            if (!isNumber(choice)) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }
            switch (Integer.parseInt(choice)) {
                case 1:
                    // Admin functionalities
                    adminMenu();
                    break;

                case 2:
                    // Renter functionalities
                    // System.out.println("Enter Your Phone number to LogIn To Your system. Thanks
                    // :)");
                    // String phn = scanner.nextLine();
                    // int index = numberCheck(phn);
                    // if (index == -1) {
                    // System.out.println("Phone number not found. Please first get yourself
                    // register !!.");
                    // break;
                    // } else
                    int index = 0;
                    RenterMenu(index);
                    break;

                case 0:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void adminMenu() {
        while (true) {
            System.out.println("\n-- Admin Menu --");
            System.out.println("\t1. Display Available Non-Rented Cars");
            System.out.println("\t2. Display All Cars in system");
            System.out.println("\t3. Add Car");
            System.out.println("\t4. Add Renter");
            System.out.println("\t5. Show Renter Information");
            System.out.println("\t6. Show All Renter Details");
            System.out.println("\t7. Remove Car");
            System.out.println("\t8. Remove Renter");
            System.out.println("\t9. Display Rented Cars");
            System.out.println("0. Exit to Main Menu");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            if (!isNumber(choice)) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }
            switch (Integer.parseInt(choice)) {
                case 1:
                    AvailableNonRentedCars();
                    break;

                case 2:
                    All_AvailableCars();
                    break;

                case 3:
                    addingCarInSystem();
                    break;

                case 4:
                    addingRenterInSystem();
                    break;

                case 5:
                    System.out.println("Enter Renter ID:");
                    int renterId = scanner.nextInt();
                    scanner.nextLine();
                    int y = FindRenter_ID(renterId, 0);
                    break;

                case 6:
                    All_Renter_inSystem();
                    break;

                case 7:
                    System.out.println("Enter Car ID to be removed:");
                    int carId = scanner.nextInt();
                    scanner.nextLine();
                    removeCar(carId);
                    break;

                case 8:
                    System.out.println("Enter Renter ID to be removed:");
                    int renterIdToRemove = scanner.nextInt();
                    scanner.nextLine();
                    removeRenter(renterIdToRemove);
                    break;

                case 9:
                    RentedCars();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void RenterMenu(int index) {
        while (true) {
            System.out.println("\n-- User Menu --");
            System.out.println("\t1. Display Non-Rented Cars");
            System.out.println("\t2. Rent a Car");
            System.out.println("\t3. Show Cars Rented By specific Renter");
            System.out.println("\t4. Return Car:");
            System.out.println("0. Exit to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            if (!isNumber(choice)) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }
            switch (Integer.parseInt(choice)) {
                case 1:
                    AvailableNonRentedCars();
                    break;

                case 2:
                    rentCarToRenter();
                    break;

                case 3:
                    System.out.println("Enter Renter ID:");
                    int renterId = scanner.nextInt();
                    scanner.nextLine();
                    int i = FindRenter_ID(renterId, 1);
                    if (i == -1) {
                        System.out.println("No renter found with this ID !!.");
                        break;
                    }
                    RentedCarsBy(i);
                    break;

                case 4:
                    ReturningCar();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ! -----------------------------------------------------------------------//
    // ! --------------------- [ Helper Functions ] ---------------------------//
    // ! -----------------------------------------------------------------------//

    public static boolean isNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) { // Check if each character is a digit
                return false;
            }
        }
        return true; // Return true if all characters are digits
    }

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.length() == 11 && isNumber(phoneNumber);
    }

    public int numberCheck(String phn) {
        int index = 0;
        for (Renter r : renters) {
            if (r.getPhoneNumber().equals(phn))
                return index;
            index++;
        }
        return -1;
    }

    public int FindCarByID(int id) {
        int index = 0;
        for (Car car : car_list) {
            if (car.getCarId() == id) {
                return index;
            }
            index++;
        }
        System.out.println("No Car In database");
        return -1;
    }

}