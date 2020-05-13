package se.kth.iv1350.pointOfSale.controller;

import java.util.ArrayList;

import java.util.List;

import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.DatabaseFailureException;
import se.kth.iv1350.pointOfSale.integration.ExternalSystemHandler;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.ItemNotFoundException;
import se.kth.iv1350.pointOfSale.integration.ItemRegistry;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.RegistryHandler;
import se.kth.iv1350.pointOfSale.model.CashRegister;
import se.kth.iv1350.pointOfSale.model.Change;
import se.kth.iv1350.pointOfSale.model.Payment;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.view.RevenueObserver;

/**
 * This is the application’s only controller class. All calls to the model pass through here.
 * 
 */
public class Controller {
	
	private Sale sale;
	private ItemRegistry itemReg;
	private CashRegister cashReg;
	private InventorySystem invSys;
	private AccountingSystem accSys;
	private Printer printer;
	private List<RevenueObserver> revenueObs = new ArrayList<>();
	
	/**
	 * Creates a new Controller instance and a new CashRegister instance with the start amount of 100.
	 * @param printer is used so that the controller can call printReceipt(); 
	 * @param exSysHandler is used to get the external systems.
	 * @param regHandler is used to get the itemregistry.
	 */
	public Controller(Printer printer, ExternalSystemHandler exSysHandler, RegistryHandler regHandler) {
		itemReg = regHandler.getItemReg();
		cashReg = new CashRegister(100);
		invSys = exSysHandler.getInvSys();
		accSys = exSysHandler.getAccSys();
		this.printer = printer;
	}
	
	/**
	 * Starts new sale.
	 */
	public void startSale() {
		sale = new Sale();
	}
	
	/**
	 * Updates the sale with the entered item by the user.
	 * @param itemID The unique itemidentifier.
	 * @param quantity The quantity of that item.
	 * @return A string containing information about the entered item.
	 * @throws OperationFailedException is thrown when a DatabaseFailureException or ItemNotFoundException is thrown from ItemRegistry.
	 */
	public String enterItem(String itemID, int quantity) throws OperationFailedException {
		String itemStatus = null;
		String itemInfo = null;
		try {
			itemStatus = itemReg.checkItem(itemID);
			
			if(itemStatus.equals("itemAlreadyRegistered")) {
				sale.addQuantity(itemID);
			}
			
			if(itemStatus.equals("OK")) {
				itemInfo = sale.updateSale(itemReg.retriveItem(itemID, quantity));
			}
			
		} catch (ItemNotFoundException | DatabaseFailureException e) {
			throw new OperationFailedException(e.getMessage(), e);
		}
		
		return itemInfo;
	}
	
	/**
	 * Ends the sale.
	 * @return The total price of the sale.
	 */
	public double endSale() {
		return sale.getTotalPrice();
	}
	
	/**
	 * This method updates the cashregister and finalizes sale by updating the external systems and returning the change.
	 * @param amount Amount paid by customer.
	 * @return Change to the customer.
	 */
	public Change makePayment(double amount) {
		Payment amountPaid = new Payment(amount);
		cashReg.addRevenueObserver(revenueObs);
		cashReg.updateBalance(amountPaid);
		Change change = sale.finalizeSale(amountPaid, invSys, accSys);
		return change;
	}
	
	/**
	 * Prints out the sale content on receipt
	 */
	public void printReceipt() {
		sale.printReceipt(printer);
	}
	
	public void addRevenueObserver(RevenueObserver obs) {
		revenueObs.add(obs);
	}
	
	public Sale getSale(){
		return sale;
	}

}
