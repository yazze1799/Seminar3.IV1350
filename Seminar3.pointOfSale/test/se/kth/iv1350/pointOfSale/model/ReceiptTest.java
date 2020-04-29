package se.kth.iv1350.pointOfSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.integration.ItemDTO;

class ReceiptTest {
	private Sale saleInstance;
	private Receipt instanceToTest;
	
	@BeforeEach
	void setUp() throws Exception {
		saleInstance = new Sale();
	}

	@AfterEach
	void tearDown() throws Exception {
		saleInstance = null;
		instanceToTest = null;
	}

	@Test
	void testCreateReceiptString() {
		ItemDTO anItem = new ItemDTO("P074T0", "Potatosalad", 1, 25.0, 25);
		Payment amountPaid = new Payment(700);
		saleInstance.updateSale(anItem);
		saleInstance.setAmountPaid(amountPaid);
		saleInstance.setChange(new Change(saleInstance.getTotalPrice(), amountPaid));
		instanceToTest = new Receipt(saleInstance);
		String result = instanceToTest.createReceiptString();
		String expString = 
				"Sale started: " + saleInstance.getSaleTime() + " \n"
				+ "Items bought: \n\n" + anItem 
				+ "\nAmount of items bought: " + saleInstance.getQuantity() + " \n" 
				+ "Running total: " + saleInstance.getTotalPrice() + " \n"
				+ "VAT for sale: " + saleInstance.getVATforSale() + " \n"
				+ "Amount paid: " + saleInstance.getAmountPaid().getAmount() + " \n"
				+ "Change: " + (-saleInstance.getChange().getAmount()) + " \n";
		assertTrue(result.contains(expString), "Wrong printout.");
	}

}
