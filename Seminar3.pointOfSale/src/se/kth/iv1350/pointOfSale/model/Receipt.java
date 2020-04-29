package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.integration.ItemDTO;

/**
 * Represent a receipt of the finale sale.
 * @author Yassin
 *
 */
public class Receipt {
	
	private Sale sale;
	
	public Receipt(Sale sale) {
		this.sale = sale;
	}
	
	/**
	 * Creates a well-formatted string with the content of the receipt. 
	 * @return the well-formated receipt string.
	 */
	public String createReceiptString() {
		StringBuilder saleInfo = new StringBuilder();
		saleInfo.append("Sale started: " + sale.getSaleTime() + " \n");
		saleInfo.append("Items bought: \n\n");
		for(ItemDTO item : sale.getItemsInSale())
			saleInfo.append(item.toString());
		saleInfo.append("\nAmount of items bought: " + sale.getQuantity() + " \n");
		saleInfo.append("Running total: " + sale.getTotalPrice() + " \n");
		saleInfo.append("VAT for sale: " + sale.getVATforSale() + " \n");
		saleInfo.append("Amount paid: " + sale.getAmountPaid().getAmount() + " \n");
		saleInfo.append("Change: " + (-sale.getChange().getAmount()) + " \n");
		return saleInfo.toString();
	}
}
