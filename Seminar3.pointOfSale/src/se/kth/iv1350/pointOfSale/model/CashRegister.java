package se.kth.iv1350.pointOfSale.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pointOfSale.view.RevenueObserver;

/**
 * Represents a cashregister in a store.
 * @author Yassin
 *
 */
public class CashRegister {
	
	private double amountInCashRegister;
	private List<RevenueObserver> revenueObs = new ArrayList<>();
	
	
	/**
	 * Creates a new instance and sets the start amount.
	 * @param startAmount
	 */
	public CashRegister(double startAmount) {
		this.amountInCashRegister = startAmount;
	}
	
	public void updateBalance(Payment amountPaid) {
		this.amountInCashRegister += amountPaid.getAmount();
		notifyObservers(amountPaid);
	}
	
	private void notifyObservers(Payment amountPaid) {
		for(RevenueObserver obs : revenueObs) {
			obs.addPayment(amountPaid);
		}
	}
	
	public void addRevenueObserver(List<RevenueObserver> observers) {
		revenueObs.clear();
		revenueObs.addAll(observers);
	}

	public double getAmountInCashRegister() {
		return amountInCashRegister;
	}
}
