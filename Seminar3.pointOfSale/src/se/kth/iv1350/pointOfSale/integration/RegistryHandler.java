package se.kth.iv1350.pointOfSale.integration;

/**
 * This class creates and handle the registers. DiscountRegistry is omitted.
 * @author Yassin
 *
 */
public class RegistryHandler {
	
	private ItemRegistry itemReg;
	/**
	 * The constructor creates a new instance of the itemregistry when called upon.
	 */
	public RegistryHandler() {
		itemReg = new ItemRegistry();
	}
	
	public ItemRegistry getItemReg() {
		return this.itemReg;
	}
	
}
