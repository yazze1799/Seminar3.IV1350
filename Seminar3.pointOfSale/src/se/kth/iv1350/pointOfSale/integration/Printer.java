package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Receipt;

/**
 * Represents a printer in a store.
 * @author Yassin
 *
 */
public class Printer {
	
	public Printer() {
		
	}
	
	/**
	 * Prints out the received receipt. This dummy implementation
 	 * prints to <code>System.out</code> instead of a printer.
	 * @param receiptToPrint
	 */
	public void printReceipt(Receipt receiptSentToPrinter) {
		System.out.println(receiptSentToPrinter.createReceiptString());
	}
}
