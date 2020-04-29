package se.kth.iv1350.pointOfSale.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;

class SaleTest {
	private Sale saleInstance;
	private ItemDTO anItem;
	private ItemDTO anotherItem;
	private Payment amountPaid;
	private InventorySystem invSys;
	private AccountingSystem accSys;
	
	@BeforeEach
	void setUp() throws Exception {
		saleInstance = new Sale();
		anItem = new ItemDTO("P074T0", "Potatosalad", 1, 25.0, 25);
		anotherItem = new ItemDTO("B00K", "Book", 1, 150.0, 6);
		amountPaid = new Payment(750);
		invSys = new InventorySystem();
		accSys = new AccountingSystem();
	}

	@AfterEach
	void tearDown() throws Exception {
		saleInstance = null;
		anItem = null;
		anotherItem = null;
		amountPaid = null;
		invSys = null;
		accSys = null;
	}

	@Test
	void testUpdateSale() {
		saleInstance.updateSale(anItem);
		saleInstance.updateSale(anotherItem);
		int quantity = saleInstance.getQuantity();
		assertEquals(2, quantity);
	}
	
	@Test
	void testUpdateTotalPrice() {
		saleInstance.updateSale(anItem);
		saleInstance.updateSale(anotherItem);
		double totalPrice = saleInstance.getTotalPrice();
		assertEquals(175, totalPrice);
	}
	
	@Test
	void testUpdateVAT() {
		saleInstance.updateSale(anItem);
		saleInstance.updateSale(anotherItem);
		double VAT = saleInstance.getVATforSale();
		assertEquals(((25 * 0.25) + (150 * 0.06)), VAT);
	}
	
	@Test
	void testAddQuantity() {
		saleInstance.updateSale(anItem);
		saleInstance.addQuantity(anItem.getItemID());
		saleInstance.addQuantity(anItem.getItemID());
		int three = 3;
		int qty = saleInstance.getQuantity();
		assertEquals(three, qty, "Item quantity not added correctly.");
	}
	
	@Test
	void testFinalizeSale() {
		anItem.setQuantity(5);
		anotherItem.setQuantity(3);
		saleInstance.updateSale(anItem);
		saleInstance.updateSale(anotherItem);
		saleInstance.addQuantity(anItem.getItemID());
		Change change = saleInstance.finalizeSale(amountPaid, invSys, accSys);
		int expectedChange = -25;
		assertEquals(expectedChange, change.getAmount(), "The change amount does not match the expected change.");
	}

}
