package se.kth.iv1350.pointOfSale.integration;

/**
 * Thrown when an item is not found in the item registry.
 * @author Yassin
 *
 */
public class ItemNotFoundException extends Exception {
	public ItemNotFoundException(String itemID) {
		super("The scanned item was not found in the item registry. ItemID: " + itemID + "\n");
	}
}
