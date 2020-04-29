package se.kth.iv1350.pointOfSale.model;

/**
 * Represent the change that is returned to the customer after payment.
 * @author Yassin
 *
 */
public class Change {
	private double amount;
	
	/**
	 * Creates a new instance and calculates the amount that should be returened to the customer.
	 * @param totalPrice of the sale.
	 * @param amountPaid by the customer
	 */
	public Change(double totalPrice, Payment amountPaid) {
		this.amount = (totalPrice - amountPaid.getAmount());
	}

	public double getAmount() {
		return amount;
	}
}
