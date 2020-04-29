package se.kth.iv1350.pointOfSale.startup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;
	
	@BeforeEach
	void setUp() throws Exception {
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}
	
	@Test
	void testMain() {
		String[] args = null;
		Main.main(args);
		String printout = printoutBuffer.toString();
		String expected = "started";
		assertTrue(printout.contains(expected), "The UI did not start correctly.");
	}

}
