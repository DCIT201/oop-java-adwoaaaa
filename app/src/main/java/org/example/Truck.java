package org.example;

public class Truck extends Vehicle{
    private double cargoCapacity; // in tons
    private static final double CARGO_RATE_PER_TON = 20.0;

    public Truck(String vehicleId, String model, double baseRentalRate, double cargoCapacity) {
        super(vehicleId, model, baseRentalRate);
        if (cargoCapacity <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be positive.");
        }
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        if (cargoCapacity <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be positive.");
        }
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of rental days must be positive.");
        }
        double cost = getBaseRentalRate() * days;
        cost += CARGO_RATE_PER_TON * cargoCapacity * days;
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public String toString() {
        return "Truck{" +
               "vehicleId='" + getVehicleId() + '\'' +
               ", model='" + getModel() + '\'' +
               ", baseRentalRate=" + getBaseRentalRate() +
               ", cargoCapacity=" + cargoCapacity +
               " tons, isAvailable=" + isAvailable() +
               '}';
    }

}
