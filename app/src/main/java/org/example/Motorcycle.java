package org.example;

public class Motorcycle extends Vehicle {
    private boolean hasHelmetIncluded;
    private static final double HELMET_CHARGE = 5.0;

    public Motorcycle(String vehicleId, String model, double baseRentalRate, boolean hasHelmetIncluded) {
        super(vehicleId, model, baseRentalRate);
        this.hasHelmetIncluded = hasHelmetIncluded;
    }


    public boolean hasHelmetIncluded() {
        return hasHelmetIncluded;
    }

    public void setHasHelmetIncluded(boolean hasHelmetIncluded) {
        this.hasHelmetIncluded = hasHelmetIncluded;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of rental days must be positive.");
        }
        double cost = getBaseRentalRate() * days;
        if (hasHelmetIncluded) {
            cost += HELMET_CHARGE * days;
        }
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
               "vehicleId='" + getVehicleId() + '\'' +
               ", model='" + getModel() + '\'' +
               ", baseRentalRate=" + getBaseRentalRate() +
               ", hasHelmetIncluded=" + hasHelmetIncluded +
               ", isAvailable=" + isAvailable() +
               '}';
    }
}
