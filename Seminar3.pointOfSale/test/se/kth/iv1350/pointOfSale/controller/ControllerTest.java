package se.kth.iv1350.pointOfSale.controller;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.integration.ExternalSystemHandler;
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
		String itemInfo = instanceToTest.enterItem("A99L3", 4);
		boolean actual = itemInfo.contains("Apple");
		assertEquals(expected, actual, "Entered item not OK");
	}
	
	@Test
	void testEnterInvalidItem() {
		boolean expected = true;
		String itemInfo = instanceToTest.enterItem("R1CE", 1);
		boolean actual = itemInfo.contains("not identified");
		assertEquals(expected, actual, "Invalid item entered as valid item.");
	}
	
	@Test
	void testEndSale() {
		instanceToTest.enterItem("A99L3", 4);
		instanceToTest.enterItem("C4K3", 1);
		double expected = 340;
		double actual = instanceToTest.endSale();
		assertEquals(expected, actual, "Total price not correct.");
	}
	
	@Test
	void testMakePayment() {
		instanceToTest.enterItem("A99L3", 4);
		instanceToTest.enterItem("C4K3", 1);
		Change change = instanceToTest.makePayment(400);
		double expected = -60;
		double actual = change.getAmount();
		assertEquals(expected, actual, "Change not correct.");
	}
	
}
