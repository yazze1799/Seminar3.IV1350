package se.kth.iv1350.pointOfSale.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.model.Sale;

class ExternalSystemHandlerTest {
	private ExternalSystemHandler instanceToTest;
	private Sale saleInstance;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;
	
	@BeforeEach
	void setUp() throws Exception {
		instanceToTest = new ExternalSystemHandler();
		saleInstance = new Sale();
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		instanceToTest = null;
		saleInstance = null;
		
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}

	@Test
	void testUpdateExternalSystems() {
		instanceToTest.updateExternalSystems(saleInstance);
		String printout = printoutBuffer.toString();
		String invExpected = "updated";
		String accExpected = "accounted";
		assertTrue(printout.contains(invExpected) && printout.contains(accExpected), "The inventory and accounting system did not update correctly.");
	}

}
