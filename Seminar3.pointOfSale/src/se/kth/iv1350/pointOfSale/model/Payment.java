package se.kth.iv1350.pointOfSale.model;

/**
 * Represent a payment for a sale.
 * @author Yassin
 *
 */
public class Payment {
	
	private double amount;
	
	/**
	 * Creates a new instance of a Payment.
	 * @param amountPaid is the amount paid by the customer.
	 */
	public Payment(double amountPaid) {
		this.amount = amountPaid;
	}

	public double getAmount() {
		return amount;
	}
	
}
