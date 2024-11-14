package booking;

import java.util.Date;

public class Payment {
    private String paymentId;
    private String bookingId;      // Associated booking ID
    private double amount;         // Payment amount
    private String paymentMethod;  // e.g., "Credit Card", "Debit Card", "PayPal"
    private Date paymentDate;
    private String paymentStatus;  // e.g., "Pending", "Completed", "Failed"

    // Constructor
    public Payment(String bookingId, double amount, String paymentMethod) {
        this.paymentId = generatePaymentId();
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = new Date();
        this.paymentStatus = "Pending";
    }

    // Method to generate a unique payment ID
    private String generatePaymentId() {
        return "PAY" + System.currentTimeMillis();
    }

    // Method to process payment
    public boolean processPayment() {
        // Simulate payment processing
        // Here, you'd integrate with a payment gateway API
        if (this.amount > 0) {
            this.paymentStatus = "Completed";
            return true;
        } else {
            this.paymentStatus = "Failed";
            return false;
        }
    }

    // Getter methods
    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    // Optional: Override toString() for easy display of payment info
    @Override
    public String toString() {
        return "Payment ID: " + paymentId +
                "\nBooking ID: " + bookingId +
                "\nAmount: $" + amount +
                "\nPayment Method: " + paymentMethod +
                "\nPayment Date: " + paymentDate +
                "\nPayment Status: " + paymentStatus;
    }
}
