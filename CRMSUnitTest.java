package system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CRMSTest {

    private CRMS system;
    private ArrayList<Car> carList;
    private ArrayList<Renter> renters;
    private counter counter;

    @BeforeEach
    public void setup() {
        counter = new counter();

        // Creating test data
        Car car1 = new Compact(counter.cars++, "Toyota", "Corolla", "2019", 50, "ABC123");
        Car car2 = new SUV(counter.cars++, "Ford", "Explorer", "2020", 100, "XYZ789");
        carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        Renter renter1 = new RegularRenter(counter.renters++, "John", "john@example.com", "11111111111", "123 Main St");
        Renter renter2 = new RegularRenter(counter.renters++, "Alice", "alice@example.com", "22222222222", "456 Elm St");
        renters = new ArrayList<>();
        renters.add(renter1);
        renters.add(renter2);

        // Initializing the system with the test data
        system = new CRMS(renters, carList, counter);
    }

    // Test case to check if a car is added successfully
    @Test
    public void testAddCar() {
        Car car3 = new LuxuryCar(counter.cars++, "BMW", "7 Series", "2021", 200, "LMN456");
        carList.add(car3);

        // Verify that the car was added successfully
        assertEquals(3, carList.size());
        assertEquals("BMW", carList.get(2).getBrand());
        assertEquals("7 Series", carList.get(2).getModel());
        assertEquals("LMN456", carList.get(2).getPlateNumber());
    }

    // Test case to check if a renter is added successfully
    @Test
    public void testAddRenter() {
        Renter renter3 = new CorporateRenter(counter.renters++, "Bob", "bob@example.com", "33333333333", "789 Oak St");
        renters.add(renter3);

        // Verify that the renter was added successfully
        assertEquals(3, renters.size());
        assertEquals("Bob", renters.get(2).getName());
        assertEquals("bob@example.com", renters.get(2).getEmail());
    }

    // Test case to check non-rented cars
    @Test
    public void testAvailableNonRentedCars() {
        // Initially both cars are non-rented
        ArrayList<Car> availableCars = new ArrayList<>();
        for (Car car : carList) {
            if (!car.isRented()) {
                availableCars.add(car);
            }
        }

        // Verify the available non-rented cars
        assertEquals(2, availableCars.size());
    }

    // Test case to remove a car from the system
    @Test
    public void testRemoveCar() {
        int initialSize = carList.size();
        system.removeCar(carList.get(0).getCarId());

        // Verify that the car was removed successfully
        assertEquals(initialSize - 1, carList.size());
    }

    // Test case to remove a renter from the system
    @Test
    public void testRemoveRenter() {
        int initialSize = renters.size();
        system.removeRenter(renters.get(0).getRenterId());

        // Verify that the renter was removed successfully
        assertEquals(initialSize - 1, renters.size());
    }

    // Test case to rent a car
    @Test
    public void testRentCar() {
        int carIndex = system.FindCarByID(carList.get(0).getCarId());
        int renterIndex = system.FindRenter_ID(renters.get(0).getRenterId(), 1);

        Car carToRent = carList.get(carIndex);
        Renter renter = renters.get(renterIndex);

        // Assume we rent the first car to the first renter
        carToRent.setRented(true);
        renter.addCar(carToRent);

        // Verify that the car is rented
        assertTrue(carToRent.isRented());
        assertEquals(1, renter.getRentedCars().size());
    }

    // Test case to check returning a rented car
    @Test
    public void testReturnCar() {
        // Simulate renting a car first
        int carIndex = system.FindCarByID(carList.get(0).getCarId());
        int renterIndex = system.FindRenter_ID(renters.get(0).getRenterId(), 1);
        Car carToRent = carList.get(carIndex);
        Renter renter = renters.get(renterIndex);
        carToRent.setRented(true);
        renter.addCar(carToRent);

        // Now simulate returning the car
        renter.removeCar(carToRent);
        carToRent.setRented(false);

        // Verify the car is returned
        assertFalse(carToRent.isRented());
        assertEquals(0, renter.getRentedCars().size());
    }
}
