package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.model.Payment;

/**
 * This is a listener inteface that recevies notifications when the revenue increases.
 * When the revenue increases, the (@link #addPayment(Payment) addPayment) method is invoked.
 * @author Yassin
 *
 */
public interface RevenueObserver {
	/**
	 * Invoked when revenue increases.
	 * @param amountPaid The amount that the revenue increased with.
	 */
	void addPayment(Payment amountPaid);
}
