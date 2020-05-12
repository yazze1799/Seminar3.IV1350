package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.model.Payment;

public interface RevenueObserver {
	void addPayment(Payment amountPaid);
}
