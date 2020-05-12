package se.kth.iv1350.pointOfSale.integration;

/**
 * 
 * @author Yassin
 *
 */
public class ItemNotFoundException extends Exception {
	
	private String itemID;
	/**
	 * 
	 * @param itemID
	 */
	public ItemNotFoundException(String itemID) {
		super("The scanned item was not found in the item registry. ItemID: " + itemID + "\n");
		this.itemID = itemID;
	}

	public String getItemID() {
		return itemID;
	}
}
