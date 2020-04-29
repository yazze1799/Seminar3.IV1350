package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Sale;

/**
 * Represent an accounting system. This class would interact with an external accounting system.
 * @author Yassin
 *
 */
public class AccountingSystem {
	
	/**
	 * This method does nothing since the task does not demand any calls to an external accounting system.
	 * @param sale contains saleinformation.
	 */
	public void accountSale(Sale sale) {
		System.out.println("Sale accounted. \n");
	}
}
