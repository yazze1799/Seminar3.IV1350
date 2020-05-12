package se.kth.iv1350.pointOfSale.model;

import java.time.LocalTime;


import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.view.RevenueObserver;

/**
 * Represent a point of sale in a store.
 * @author Yassin
 *
 */
public class Sale {
	
	private LocalTime saleTime;
	private List<ItemDTO> itemsInSale = new ArrayList<>();
	private double totalPrice;
	private double VATforSale;
	private Payment amountPaid;
	private Change change;
	private Receipt saleReceipt;

	/**
	 * Creates a new instance of Sale. Sets time of sale, and running total to 0.
	 */
	public Sale() {
		saleTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
		totalPrice = 0;
		VATforSale = 0;
	}
	
	/**
	 * Updates saleinformation. List of itemsInSale, the totalPrice and the VATforSale is updated.
	 * @param item is added to sale. The object contains attributes that is used when updating the saleinformation.
	 * @return a String that contains the information about the items that are in the current sale.
	 */
	public String updateSale(ItemDTO item) {
		itemsInSale.add(item);
		updatePriceAndVAT(item);
		return itemsInSale.toString();
	}
	
	/**
	 * Updates the total price and VAT for the current sale.
	 * @param item contains information about each item's price and VAT rate.
	 */
	private void updatePriceAndVAT(ItemDTO item) {
		totalPrice = getTotalPrice() +  (item.getQuantity() * item.getPrice());
		VATforSale = getVATforSale() + (item.getQuantity() * (item.getPrice() * (item.getVatRate() / 100)));
	}
	
	
	/**
	 * Reduces the total price and VAT for the current sale with the old quantity of an item, i.e the quanity before it added with one.
	 * @param item contains information about each item's price and VAT rate.
	 */
	private void updatePriceAndVATSub(ItemDTO item) {
		totalPrice -= ((item.getQuantity() - 1) * item.getPrice());
		VATforSale -= ((item.getQuantity() - 1) * (item.getPrice() * (item.getVatRate() / 100)));
	}
	/**
	 * This method is called by the controller when a item has already been entered once in the sale. The method adds the quantity of that item
	 * by one.
	 * @param itemID is used to identify which item is adding it's quantity.
	 */
	public void addQuantity(String itemID) {
		ItemDTO itemToUpdateSale = null;
		for(ItemDTO searcheditem : itemsInSale) {
			if(itemID.equals(searcheditem.getItemID())) {
				searcheditem.addQuantity();
				itemToUpdateSale = searcheditem;
			}
		}
		
		itemsInSale.remove(itemToUpdateSale);
		updateSale(itemToUpdateSale);
		updatePriceAndVATSub(itemToUpdateSale);
	}

	/**
	 * Finalizes the sale. 
	 * First the attributes amountPaid and change are set so it can be viewed on the receipt. 
	 * Second the external systems are updated: the inventory system and the accounting system.
	 * 
	 * @param amountPaid is an object of Payment. Contains the amount paid by customer
	 * @param invSys is an instance of InventorySystem that is created in startup. Used when calling method updateExternalSystems.
	 * @param accSys is an instance of AccountingSystem that is created in startup. Used when calling method updateExternalSystems.
	 * @param printer is an instance of Printer that is created upon startup. Used when calling method printReceipt.
	 * @return change to customer.
	 */
	public Change finalizeSale(Payment amountPaid, InventorySystem invSys, AccountingSystem accSys) {
		this.amountPaid = amountPaid;
		this.change = new Change(totalPrice, amountPaid);
		updateExternalSystems(invSys, accSys);
		
		
		return change;
	}


	private void updateExternalSystems(InventorySystem invSys, AccountingSystem accSys) {
		invSys.updateInventory(this);
		accSys.accountSale(this);
	}
	
	/**
	 * Prints the receipt.
	 * @param printer is created on startup. A reference is required so that the method can call the operation printReceipt in Printer.
	 */
	public void printReceipt(Printer printer) {
		saleReceipt = new Receipt(this);
		printer.printReceipt(saleReceipt);
	}
	
	/**
	 * Method that calculates total amount of items in sale.
	 * @return amount of items in sale
	 */
	public int getQuantity() {
		int qty = 0;
		for(ItemDTO item : itemsInSale) {
			qty += item.getQuantity();
		}
		return qty;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public double getVATforSale() {
		return VATforSale;
	}
	
	public List<ItemDTO> getItemsInSale() {
		return itemsInSale;
	}

	public Payment getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Payment amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public LocalTime getSaleTime() {
		return saleTime;
	}
}
