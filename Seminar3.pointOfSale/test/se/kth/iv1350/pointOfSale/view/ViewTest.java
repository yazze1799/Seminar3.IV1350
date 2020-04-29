package se.kth.iv1350.pointOfSale.view;

import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.ExternalSystemHandler;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.RegistryHandler;

class ViewTest {
	private View instanceToTest;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;
	
	@BeforeEach
	void setUp() throws Exception {
		Printer printer = new Printer();
		ExternalSystemHandler exSysHandler = new ExternalSystemHandler();
		RegistryHandler regHandler = new RegistryHandler();
		Controller contr = new Controller(printer, exSysHandler, regHandler);
		instanceToTest = new View(contr);
		
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		instanceToTest = null;
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}

	@Test
	void testRunFakeExecution() {
		instanceToTest.runFakeExecution();
		String printout = printoutBuffer.toString();
		String expected = "started";
		assertTrue(printout.contains(expected), "The UI did not start correctly.");
	}

}
