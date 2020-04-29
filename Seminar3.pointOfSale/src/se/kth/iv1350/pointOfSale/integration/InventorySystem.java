package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Sale;

/**
 * Represent an inventory system. This class would interact with an external inventory system.
 * @author Yassin
 *
 */
public class InventorySystem {
	
	/**
	 * This method does nothing since the task does not demand any calls to an external inventory system.
	 * @param sale contains saleinformation.
	 */
	public void updateInventory(Sale sale) {
		System.out.println("Inventory system updated. \n");
	}
}
