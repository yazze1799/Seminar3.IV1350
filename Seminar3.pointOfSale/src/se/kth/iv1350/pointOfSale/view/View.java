package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.controller.Controller;

/**
 * The application does not have a view. This is a placeholder for the entire view.
 * @author Yassin
 *
 */
public class View {
	
	public Controller contr;
	
	/**
	 * Creates a new instance.
	 * @param contr The controller that is used for all operations.
	 */
	public View(Controller contr) {
		this.contr = contr;
	}
	
	/**
	 * Simulates user inputs that calls to all the system operations.
	 */
	public void runFakeExecution() {
		contr.startSale();
		System.out.println("A new sale has been started.\n");
		contr.enterItem("A99L3", 10);
		contr.enterItem("P074T0", 6);
		contr.enterItem("C00K13", 2);
		contr.enterItem("A99L3", 1);
		contr.enterItem("C4K3", 1);
		contr.enterItem("P074T0", 1);
		System.out.println("All items has been entered.\n");
		contr.endSale();
		System.out.println("Sale is ended.\n");
		contr.makePayment(900);
		System.out.println("Payment is made and sale is finalized.\n");
		System.out.println("Receipt: \n");
		contr.printReceipt();
	}
}
