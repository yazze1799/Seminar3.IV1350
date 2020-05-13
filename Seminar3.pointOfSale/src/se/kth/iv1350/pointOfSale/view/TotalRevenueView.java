package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.model.Payment;

/**
 * Shows the total revenue since the program started.
 * @author Yassin
 *
 */
public class TotalRevenueView implements RevenueObserver{
	private double totalRevenue;
	
	@Override
	public void addPayment(Payment amountPaid) {
		totalRevenue += amountPaid.getAmount();
		printTotalRevenue();
	}

	private void printTotalRevenue() {
		System.out.println("****** DISPLAY ******");
        System.out.println("*** TOTAL REVENUE ***");
        System.out.println("   Amount: " + totalRevenue);
        System.out.println("*********************\n");
	}
	
}
