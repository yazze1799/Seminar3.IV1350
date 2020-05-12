package se.kth.iv1350.pointOfSale.integration;

public class DatabaseFailureException extends Exception {
	
	private String itemID;
	/**
	 * 
	 * @param itemID
	 */
	public DatabaseFailureException(String itemID) {
		super("The scanned item is invalid. ItemID: " + itemID + "\n");
		this.itemID = itemID;
	}

	public String getItemID() {
		return itemID;
	}
}
