package se.kth.iv1350.pointOfSale.model;

/**
 * Represents a cashregister in a store.
 * @author Yassin
 *
 */
public class CashRegister {
	
	private double amountInCashRegister;
	
	/**
	 * Creates a new instance and sets the start amount.
	 * @param startAmount
	 */
	public CashRegister(double startAmount) {
		this.amountInCashRegister = startAmount;
	}
	
	public void updateBalance(Payment amountPaid) {
		this.amountInCashRegister += amountPaid.getAmount();
	}

	public double getAmountInCashRegister() {
		return amountInCashRegister;
	}
}
