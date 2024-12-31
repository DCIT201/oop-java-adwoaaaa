package org.example;

public class Car extends Vehicle {
    private boolean hasAirConditioning;
    private static final double AIR_CONDITIONING_CHARGE = 10.0;

    public Car(String vehicleId, String model, double baseRentalRate, boolean hasAirConditioning ){
        super(vehicleId, model, baseRentalRate);
        this.hasAirConditioning = hasAirConditioning;
    }

    public boolean hasAirConditioning(){
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning){
        this.hasAirConditioning = hasAirConditioning;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of rental days must be positive.");
        }
        double cost = getBaseRentalRate() * days;
        if (hasAirConditioning) {
            cost += AIR_CONDITIONING_CHARGE * days;
        }
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public String toString() {
        return "Car{" +
               "vehicleId='" + getVehicleId() + '\'' +
               ", model='" + getModel() + '\'' +
               ", baseRentalRate=" + getBaseRentalRate() +
               ", hasAirConditioning=" + hasAirConditioning +
               ", isAvailable=" + isAvailable() +
               '}';
    }
}
