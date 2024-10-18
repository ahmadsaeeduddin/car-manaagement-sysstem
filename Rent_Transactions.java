package system;


public class Rent_Transactions {
    Car car;
    Renter renter;
    double totalDriven;
    double totalCost;

    public Rent_Transactions(Car car, Renter renter, double drivenKM) {
        this.car = car;
        this.renter = renter;
        this.totalDriven = drivenKM;
        totalCost = 0;
    }

    public double calculateTotalCost(int damage, int damagePercent) {
        totalCost += car.calculateRent(totalDriven);
        if (damage == 1) {
            if (car instanceof Compact) {
                System.out.println(totalCost);
                totalCost += (totalCost * car.damagecost() * (damagePercent / 100.0));
                return totalCost;
            }
            double s = car.getTotalRent() - (totalCost * car.damagecost() * (damagePercent));
            if (s < 0.0) {
                totalCost += (-1 * s);
            } else {
                totalCost += s;
            }
            return totalCost;
        }
        totalCost += car.getTotalRent();
        return totalCost;
    }
}