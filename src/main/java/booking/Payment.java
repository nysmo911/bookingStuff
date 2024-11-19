package booking;

import java.util.Date;

/** Payment.userProfile.java
 * @author Andres Feldstedt
 * @version 1.0
 */
public class Payment {
    private String paymentId;
    private String bookingId;      // Associated booking ID
    private double amount;         // Payment amount
    private String paymentMethod;  // e.g., "Credit Card", "Debit Card", "PayPal"
    private Date paymentDate;
    private String paymentStatus;  // e.g., "Pending", "Completed", "Failed"

    /**
     * Constructor Method
     * @param bookingId users bookingID
     * @param amount dollar amount
     * @param paymentMethod
     */
    public Payment(String bookingId, double amount, String paymentMethod) {
        this.paymentId = generatePaymentId();
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = new Date();
        this.paymentStatus = "Pending";
    }

    /**
     * Method to generate a unique payment ID
     * @return PAY $Dollar Amount
     */
    private String generatePaymentId() {
        return "PAY" + System.currentTimeMillis();
    }

    /**
     * Method to Process Payment
     * @return paymentStatus whether payment has been processed or not
     */
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

    /**
     * getPaymentId
     * @return paymentId
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * getBookingId
     * @return bookingId
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * getAmount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }
    /**
     * getPaymentMethod
     * @return paymentMethod preferred payment method by user
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }
    /**
     * getPaymentDate
     * @return paymentDate date payment was made
     */
    public Date getPaymentDate() {
        return paymentDate;
    }
    /**
     * getPaymentStatus
     * @return paymentStatus payment completed or not
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }
    /**
     * toString
     * @return paymentId
     * @return bookingId
     * @return amount
     * @return paymentMethod
     * @return paymentDate
     * @return paymentStatus
     */
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
