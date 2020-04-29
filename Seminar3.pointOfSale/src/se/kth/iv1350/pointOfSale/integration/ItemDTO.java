package se.kth.iv1350.pointOfSale.integration;

/**
 * Represents an item.
 * @author Yassin
 *
 */
public class ItemDTO {
	
	private String itemID;
	private String name;
	private int quantity;
	private double price;
	private double vatRate;
	private boolean hasBeenScanned;
	
	public ItemDTO(String itemID, String name, int quantity, double price, double vatRate) {
		this.itemID = itemID;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.vatRate = vatRate;
		this.setHasBeenScanned(false);
	}
	
	public String getItemID() {
		return itemID;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public double getVatRate() {
		return vatRate;
	}

	public boolean isScanned() {
		return hasBeenScanned;
	}

	public void setHasBeenScanned(boolean hasBeenScanned) {
		this.hasBeenScanned = hasBeenScanned;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity() {
		this.quantity += 1;
	}
	
	public String toString() {
		return name + " | " + "Qty: " + quantity + " | Price: " + price + " | VAT: " + vatRate + "\n";
	}
	
}
