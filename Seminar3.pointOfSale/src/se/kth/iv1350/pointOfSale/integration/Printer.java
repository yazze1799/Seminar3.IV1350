package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Receipt;

/**
 * A Singleton that creates an instance of a printer in a store.
 * @author Yassin
 *
 */
public class Printer {
	
	private static final Printer INSTANCE = new Printer();
	
	private Printer() {
		
	}
	
	/**
	 * Prints out the received receipt. This dummy implementation
 	 * prints to <code>System.out</code> instead of a printer.
	 * @param receiptToPrint
	 */
	public void printReceipt(Receipt receiptSentToPrinter) {
		System.out.println(receiptSentToPrinter.createReceiptString());
	}
	
	/**
	 * @return the only intance of this singleton.
	 */
	public static Printer getPrinterInstance() {
		return INSTANCE;
	}
}
