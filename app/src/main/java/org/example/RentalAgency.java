package org.example;

import java.util.ArrayList;
import java.util.List;

public class RentalAgency {
    private List<Vehicle> vehicleFleet;
    private List<Customer> customers;

    public RentalAgency() {
        this.vehicleFleet = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleFleet.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers; // Added getter for customers
    }

    List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicleFleet) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public void rentVehicle(String vehicleId, String customerId, int rentalDays) {
        Customer customer = findCustomerById(customerId);
        Vehicle vehicle = findVehicleById(vehicleId);

        if (customer == null || vehicle == null) {
            throw new IllegalArgumentException("Customer or Vehicle not found.");
        }

        if (!customer.isEligibleForRental()) {
            throw new IllegalStateException("Customer is not eligible for rental.");
        }

        if (!vehicle.isAvailable()) {
            throw new IllegalStateException("Vehicle is not available for rental.");
        }

        RentalTransactions transaction = new RentalTransactions(vehicle, customer, rentalDays);
        customer.addRentalTransaction(transaction);
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public Vehicle findVehicleById(String vehicleId) {
        for (Vehicle vehicle : vehicleFleet) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

    public void returnVehicle(String vehicleId, String customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found.");
        }

        for (RentalTransactions transaction : customer.getRentalHistory()) {
            if (transaction.getVehicle().getVehicleId().equals(vehicleId) && !transaction.isReturned()) {
                transaction.returnVehicle();
                return;
            }
        }

        throw new IllegalStateException("No active rental found for the given vehicle.");
    }
}
