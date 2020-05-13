package se.kth.iv1350.pointOfSale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemRegistryTest {
	private ItemRegistry itemReg;
	private String validItemID = "A99L3";
	private String invalidItemID = "B4N4N4";
	private String ok = "OK";
	private String itemNotExist = "itemNotIdentified";
	private String itemAlreadyReg = "itemAlreadyRegistered";

	
	@BeforeEach
	void setUp() throws Exception {
		itemReg = new ItemRegistry();
	}

	@AfterEach
	void tearDown() throws Exception {
		itemReg = null;
	}

	@Test
	void testCheckItemExist() {
		boolean expected = true;
		boolean actual = true;
		try {
			actual = ok.equals(itemReg.checkItem(validItemID));
			assertEquals(expected, actual, "Item not found in itemRegistry.");
		} catch (ItemNotFoundException e) {
			fail("Exception thrown");
		} catch (DatabaseFailureException e) {
			fail("Exception thrown");
		}
		
	}
	

	@Test
	void testCheckItemDoesNotExist() {
		try {
			itemNotExist.equals(itemReg.checkItem(invalidItemID));
			fail("Nonexistent item exist");
		} catch (ItemNotFoundException e) {
			assertTrue(e.getMessage().contains("item was not found") && e.getMessage().contains(invalidItemID), "Wrong exception message.");
		} catch (DatabaseFailureException e) {
			fail("Wrong type of exception thrown.");
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testItemAlreadyRegistered() {
		boolean expected = true;
		try {
			itemReg.checkItem(validItemID);
		} catch (ItemNotFoundException | DatabaseFailureException e) {
			fail("Exception thrown");
		} 
		boolean actual = false;
		try {
			actual = itemAlreadyReg.equals(itemReg.checkItem(validItemID));
		} catch (ItemNotFoundException | DatabaseFailureException e) {
			fail("Exception thrown");
			e.printStackTrace();
		}
		assertEquals(expected, actual, "Item was not already registered.");
	}
	
	@Test
	void testItemNotAlreadyRegistered() {
		boolean expected = false;
		boolean actual = true;
		try {
			actual = itemAlreadyReg.equals(itemReg.checkItem(validItemID));
		} catch (ItemNotFoundException | DatabaseFailureException e) {
			fail("Exception thrown");
			e.printStackTrace();
		} 
		assertEquals(expected, actual, "Item was already registered.");
	}
	
	@Test
	void testRetriveItem() {
		Object object = itemReg.retriveItem(validItemID, 1);
		assertTrue(object instanceof ItemDTO, "Object not instace of ItemDTO.");
	}
	
	@Test
	void testRetriveInvalidItem() {
		Object object = itemReg.retriveItem(invalidItemID, 1);
		assertTrue(object == null, "An invalid item was retrived.");
	}

}
