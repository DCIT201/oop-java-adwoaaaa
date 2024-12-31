package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;
    private List<Integer> ratings; // List to store customer ratings

    public Vehicle(String vehicleId, String model, double baseRentalRate) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
        this.ratings = new ArrayList<>();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate cannot be negative.");
        }
        this.baseRentalRate = baseRentalRate;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }

    public abstract double calculateRentalCost(int days);

    public abstract boolean isAvailableForRental();

    @Override
    public String toString() {
        return "Vehicle{" + "vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", baseRentalRate=" + baseRentalRate +
                ", isAvailable=" + isAvailable +
                ", averageRating=" + getAverageRating() + '}';
    }
}