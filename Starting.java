package system;
import java.util.ArrayList;

public class Starting {
    public static void main(String[] args) {
        counter counter = new counter();
        Car car1 = new Compact(counter.cars++, "Toyota", "Corolla", "2019", 50, "ABC123");
        Car car2 = new SUV(counter.cars++, "Ford", "Explorer", "2020", 100, "XYZ789");
        Car car3 = new LuxuryCar(counter.cars++, "BMW", "7 Series", "2021", 300, "LMN456");

        ArrayList<Car> car_list = new ArrayList<Car>();
        car_list.add(car1);
        car_list.add(car2);
        car_list.add(car3);

        Renter r1 = new RegularRenter(counter.renters++, "John", "john@example.com", "11111111111", "123 Main St");
        Renter r2 = new RegularRenter(counter.renters++, "Alice", "alice.smith@example.com", "22222222222",
                "456 Oak St");
        Renter r3 = new FrequentRenter(counter.renters++, "John", "john@example.com", "33333333333", "789 Pine St");
        Renter r4 = new FrequentRenter(counter.renters++, "Lisa", "lisa@example.com", "44444444444", "987 Elm St");
        Renter r5 = new CorporateRenter(counter.renters++, "Ayesha", "ayesha@corporate.com", "55555555555",
                "101 Business Blvd");
        Renter r6 = new CorporateRenter(counter.renters++, "Ali", "Ali@corporate.com", "555-3333",
                "202 Industrial Ave");
        ArrayList<Renter> renters = new ArrayList<Renter>();
        renters.add(r1);
        renters.add(r2);
        renters.add(r3);
        renters.add(r4);
        renters.add(r5);
        renters.add(r6);

        renters.get(0).addCar(car_list.get(0));

        // for (Renter r : renters) {
        // r.print_details();
        // System.out.println("================================");
        // }

        CRMS system = new CRMS(renters, car_list, counter);
        system.start();
        // for (Car car : car_list) {
        // System.out.println(car.getDetails());
        // System.out.println("================================");
        // }
        // for (Renter r : renters) {
        // r.print_details();
        // System.out.println("================================");
        // }
        System.out.println("Thank you for visiting Car Renter service, I hope u didn't faced any inconvenience :)");
    }
}
