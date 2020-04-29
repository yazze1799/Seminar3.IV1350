package se.kth.iv1350.pointOfSale.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemRegistry contains hardcoded items that are registered in the store. A list of ItemDTO objects is stored in itemsInReg.
 * 
 * @author Yassin
 *
 */
public class ItemRegistry {
	
	private List<ItemDTO> itemsInReg = new ArrayList<>();
	
	public ItemRegistry() {
		addItems();
	}
	
	/**
	 * Adds hardcoded items to itemregistry.
	 */
	private void addItems() {
		itemsInReg.add(new ItemDTO("A99L3", "Royal Gala Apple", 1, 10.00, 25));
		itemsInReg.add(new ItemDTO("C4SHNU72", "Bag of Cashewnuts", 1, 55.00, 25));
		itemsInReg.add(new ItemDTO("P074T0", "Potatosalad", 1, 25.00, 25));
		itemsInReg.add(new ItemDTO("C4K3", "Cake", 1, 300.00, 25));
		itemsInReg.add(new ItemDTO("C00K13", "Cookie", 1, 10.00, 25));
		itemsInReg.add(new ItemDTO("B00K", "Book", 1, 100.0, 6));
	}
	
	/**
	 * Checks if the input itemID matches any item in itemregistry.
	 * @param itemID is entered by the user.
	 * @return message whether items is: not identified, already registered, or OK.
	 * 	 
	 * */
	public String checkItem(String itemID) {
		for(ItemDTO item : itemsInReg) {
			if(itemID.equals(item.getItemID()) && item.isScanned()) {
				return "itemAlreadyRegistered";
			}	
			
			else if(itemID.equals(item.getItemID())) {
				item.setHasBeenScanned(true);
				return "OK";
			}
		}
		return "itemNotIdentified";
	}
	
	/**
	 * This method returns the item and sets the quantity of that item.
	 * @param itemID The unique item identifier.
	 * @param quantity The quantity of that item.
	 * @return the item that matches the unique item ID.
	 */
	public ItemDTO retriveItem(String itemID, int quantity) {
		for(ItemDTO item : itemsInReg) {
			if(itemID.equals(item.getItemID())) {
				item.setQuantity(quantity);
				return item;
			}
		}
		return null;
	}
}
