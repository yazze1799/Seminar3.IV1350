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
		boolean actual = ok.equals(itemReg.checkItem(validItemID));
		assertEquals(expected, actual, "Item not found in itemRegistry.");
	}
	

	@Test
	void testCheckItemDoesNotExist() {
		boolean expected = true;
		boolean actual = itemNotExist.equals(itemReg.checkItem(invalidItemID));
		assertEquals(expected, actual, "Item found in itemRegistry.");
	}
	
	@Test
	void testItemAlreadyRegistered() {
		boolean expected = true;
		itemReg.checkItem(validItemID);
		boolean actual = itemAlreadyReg.equals(itemReg.checkItem(validItemID));
		assertEquals(expected, actual, "Item was not already registered.");
	}
	
	@Test
	void testItemNotAlreadyRegistered() {
		boolean expected = false;
		boolean actual = itemAlreadyReg.equals(itemReg.checkItem(validItemID));
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
