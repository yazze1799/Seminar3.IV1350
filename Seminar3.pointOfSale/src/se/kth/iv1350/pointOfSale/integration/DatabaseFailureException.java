package se.kth.iv1350.pointOfSale.integration;
/**
 * Thrown when an invalid item is entered.
 * @author Yassin
 *
 */
public class DatabaseFailureException extends Exception {
	public DatabaseFailureException(String itemID) {
		super("Database failure. Caused when entering following itemID: " + itemID + "\n");
	}
}
