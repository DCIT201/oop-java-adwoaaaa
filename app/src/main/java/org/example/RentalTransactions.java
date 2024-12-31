package org.example;

import java.time.LocalDate;

public class RentalTransactions {
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate rentalDate;
    private int rentalDays;
    private boolean isReturned;

    public RentalTransactions(Vehicle vehicle, Customer customer, int rentalDays) {
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than zero.");
        }

        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDate = LocalDate.now();
        this.rentalDays = rentalDays;
        this.isReturned = false;

        vehicle.setAvailable(false);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void returnVehicle() {
        this.isReturned = true;
        vehicle.setAvailable(true); // Mark vehicle as available
        customer.increaseLoyaltyPoints(); // Increase loyalty points
    }

    public double calculateTotalCost() {
        return vehicle.calculateRentalCost(rentalDays);
    }
}