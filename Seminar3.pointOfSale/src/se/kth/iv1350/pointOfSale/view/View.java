package se.kth.iv1350.pointOfSale.view;

import java.io.IOException;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.ItemNotFoundException;
import se.kth.iv1350.pointOfSale.integration.OperationFailedException;
import se.kth.iv1350.pointOfSale.utilities.FileLog;

/**
 * The application does not have a view. This is a placeholder for the entire view.
 * @author Yassin
 *
 */
public class View {
	
	public Controller contr;
	public FileLog fileLog;
	
	/**
	 * Creates a new instance.
	 * @param contr The controller that is used for all operations.
	 */
	public View(Controller controller) {
		this.contr = controller;
		contr.addRevenueObserver(new TotalRevenueView());
		
		try {
			fileLog = new FileLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Simulates user inputs that calls to all the system operations.
	 */
	public void runFakeExecution() {
		contr.startSale();
		System.out.println("A new sale has been started.\n");
		
		enterItem("A99L3", 10);
		enterItem("P074T0", 6);
		enterItem("INV4L1D", 4);
		enterItem("C00K13", 2);
		enterItem("NOTINREG", 1);
		enterItem("A99L3", 1);
		enterItem("C4K3", 1);
		
		

		System.out.println("All items has been entered.\n");
		contr.endSale();
		System.out.println("Sale is ended.\n");
		contr.makePayment(650);
		System.out.println("Payment is made and sale is finalized.\n");
		System.out.println("Receipt: \n");
		contr.printReceipt();
		System.out.println("###################################### \n");
		
		//SALE 2
		
		contr.startSale();
		System.out.println("A new sale has been started.\n");
		
		enterItem("B00K", 1);
		
		System.out.println("All items has been entered.\n");
		contr.endSale();
		System.out.println("Sale is ended.\n");
		contr.makePayment(300);
		System.out.println("Payment is made and sale is finalized.\n");
		System.out.println("Receipt: \n");
		contr.printReceipt();
		System.out.println("######################################");
	}
	
	private void handleException(Exception e) {
		System.err.println(e.getMessage());
		fileLog.logException(e);
	}
	
	private void enterItem(String itemID, int qty) {
		try{
			System.out.println(contr.enterItem(itemID, qty));
		}
		
		catch(OperationFailedException e) {
			handleException(e);
		}
	}
}
