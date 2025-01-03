/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private RentalAgency rentalAgency;

    @BeforeEach
    void setUp() {
        rentalAgency = new RentalAgency();

        // Add test vehicles
        rentalAgency.addVehicle(new Car("C001", "Toyota Camry", 50.0, true));
        rentalAgency.addVehicle(new Motorcycle("M001", "Yamaha R1", 30.0, true));

        // Add a test customer
        rentalAgency.addCustomer(new Customer("CUS1", "Tracy Sey", "tsey@example.com"));
    }

    @Test
    void testRentVehicleSuccessfully() {
        // Mock user input
        String vehicleId = "C001";    // Vehicle ID
        String customerId = "CUS1";   // Customer ID
        int rentalDays = 3;           // Rental duration

        Vehicle vehicleToRent = rentalAgency.findVehicleById(vehicleId);
        Customer customer = rentalAgency.findCustomerById(customerId);

        // Ensure vehicle and customer exist
        assertNotNull(vehicleToRent, "Vehicle not found!");
        assertNotNull(customer, "Customer not found!");

        // Simulate the rental process
        try {
            rentalAgency.rentVehicle(vehicleId, customerId, rentalDays);

            // Check if the vehicle is now unavailable
            vehicleToRent = rentalAgency.findVehicleById(vehicleId); // Refetch to get updated state
            assertFalse(vehicleToRent.isAvailable(), "Vehicle should be unavailable after renting.");

            // Check if the customer now has rental history
            customer = rentalAgency.findCustomerById(customerId); // Refetch customer to get updated state
            assertTrue(customer.getRentalHistory().size() > 0, "Customer's rental history should contain the rented vehicle.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            fail("Failed to rent vehicle: " + e.getMessage());
        }
    }

    @Test
void testRentVehicleWhenVehicleNotAvailable() {
    // Mock user input
    String vehicleId = "C001";    // Vehicle ID
    String customerId = "CUS1";   // Customer ID
    int rentalDays = 3;           // Rental duration

    // Set the vehicle to not available
    Vehicle vehicleToRent = rentalAgency.findVehicleById(vehicleId);
    if (vehicleToRent != null) {
        vehicleToRent.setAvailable(false); // Mark as unavailable
    }

    // Fetch the vehicle and customer
    Vehicle vehicle = rentalAgency.findVehicleById(vehicleId);
    Customer customer = rentalAgency.findCustomerById(customerId);

    // Ensure the vehicle and customer exist
    assertNotNull(vehicle, "Vehicle not found!");
    assertNotNull(customer, "Customer not found!");

    // Attempt to rent the vehicle and expect an exception
    assertThrows(IllegalStateException.class, () -> {
        rentalAgency.rentVehicle(vehicleId, customerId, rentalDays);
    });
}

    @Test
    void testAddCustomer() {
        // Simulate user input for adding a new customer
        String name = "Theonah Greenfield";
        String email = "tfield@example.com";
        
        // Generate customer ID
        String customerId = "CUS" + (rentalAgency.getCustomers().size() + 1);
        Customer newCustomer = new Customer(customerId, name, email);
        rentalAgency.addCustomer(newCustomer);

        // Assert that the customer was added successfully
        assertNotNull(newCustomer, "New customer should not be null.");
        assertEquals("Theonah Greenfield", newCustomer.getName(), "Customer name should match.");
        assertEquals("tfield@example.com", newCustomer.getEmail(), "Customer email should match.");
    }

    @Test
    void testRentVehicleWhenVehicleNotFound() {
        // Mock user input for a non-existing vehicle
        String vehicleId = "INVALID_ID";    // Non-existing vehicle ID
        String customerId = "CUS1";          // Customer ID
        int rentalDays = 3;                  // Rental duration

        // Attempt to rent a non-existing vehicle and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            rentalAgency.rentVehicle(vehicleId, customerId, rentalDays);
        });
    }

    @Test
    void testCustomerNotFoundForRent() {
        // Mock user input for a non-existing customer
        String vehicleId = "C001";    // Vehicle ID
        String customerId = "INVALID_CUS";  // Non-existing customer ID
        int rentalDays = 3;                  // Rental duration

        // Attempt to rent a vehicle with a non-existing customer and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            rentalAgency.rentVehicle(vehicleId, customerId, rentalDays);
        });
    }
}