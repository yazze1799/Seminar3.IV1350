package se.kth.iv1350.pointOfSale.controller;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.integration.ExternalSystemHandler;
import se.kth.iv1350.pointOfSale.integration.OperationFailedException;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.RegistryHandler;
import se.kth.iv1350.pointOfSale.model.Change;

class ControllerTest {
	
	private Controller instanceToTest;

	@BeforeEach
	void setUp() throws Exception {
		Printer printer = new Printer();
		ExternalSystemHandler exSysHandler = new ExternalSystemHandler();
		RegistryHandler regHandler = new RegistryHandler();
		instanceToTest = new Controller(printer, exSysHandler, regHandler);
		instanceToTest.startSale();
	}

	@AfterEach
	void tearDown() throws Exception {
		instanceToTest = null;
	}

	@Test
	void testEnterItem() {
		boolean expected = true;
		try {
			String itemInfo = instanceToTest.enterItem("P074T0", 4);
			boolean actual = itemInfo.contains("Potatosalad");
			assertEquals(expected, actual, "Entered item not OK");
		} catch (OperationFailedException e) {

		}
		
	}
	
	@Test
	void testEnterNonExistingItem() {
		try {
			instanceToTest.enterItem("R1CE", 1);
			fail("Exception not thrown when it should have.");
		} catch (OperationFailedException e) {
			assertTrue(e.getMessage().contains("item was not found") , "Nonexisting item entered as valid item.");
		}
		
	}
	
	@Test
	void testEndSale() {
		try {
			instanceToTest.enterItem("A99L3", 4);
		} catch (OperationFailedException e) {

		}
		
		try {
			instanceToTest.enterItem("C4K3", 1);;
		} catch (OperationFailedException e) {

		}
		
		double expected = 340;
		double actual = instanceToTest.endSale();
		assertEquals(expected, actual, "Total price not correct.");
	}
	
	@Test
	void testMakePayment() {

		try {
			instanceToTest.enterItem("B00K", 1);;
		} catch (OperationFailedException e) {

		}
		
		Change change = instanceToTest.makePayment(200);
		double expected = -100;
		double actual = change.getAmount();
		assertEquals(expected, actual, "Change not correct.");
	}
	
}
