package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Sale;

/**
 * This class creates and handle the external systems: InventorySystem and AccountingSystem.
 * @author Yassin
 *
 */
public class ExternalSystemHandler {

	private InventorySystem invSys;
	private AccountingSystem accSys;
	
	/**
	 * The constructor creates new instances of the systems when called upon.
	 */
	public ExternalSystemHandler() {
		invSys = new InventorySystem();
		accSys = new AccountingSystem();
	}

	public InventorySystem getInvSys() {
		return invSys;
	}

	public AccountingSystem getAccSys() {
		return accSys;
	}
	
	/**
	 * Updates the external systems when a sale is finalized.
	 * @param sale The sale to update the systems with.
	 */
	public void updateExternalSystems(Sale sale) {
		invSys.updateInventory(sale);
		accSys.accountSale(sale);
	}
}
