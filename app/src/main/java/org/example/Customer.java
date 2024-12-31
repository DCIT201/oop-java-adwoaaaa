package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private int loyaltyPoints; // Loyalty points for the customer
    private List<RentalTransactions> rentalHistory;

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.loyaltyPoints = 0; // Initialize loyalty points to 0
        this.rentalHistory = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public List<RentalTransactions> getRentalHistory() {
        return rentalHistory;
    }

    public void addRentalTransaction(RentalTransactions transaction) {
        rentalHistory.add(transaction);
    }

    public boolean isEligibleForRental() {
        for (RentalTransactions transaction : rentalHistory) {
            if (!transaction.isReturned()) {
                return false;
            }
        }
        return true;
    }

    public void increaseLoyaltyPoints() {
        loyaltyPoints++;
    }
}